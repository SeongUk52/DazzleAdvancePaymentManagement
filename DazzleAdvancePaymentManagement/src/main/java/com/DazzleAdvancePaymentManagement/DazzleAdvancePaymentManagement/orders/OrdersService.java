package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.CustomerRepository;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.GoodsRepository;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.GoodsService;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog.GoodsLog;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog.GoodsLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final GoodsRepository goodsRepository;
    private final GoodsLogService goodsLogService;

    public void createNewOrders(String customerName,String customerJob,String goodsName,String goodsCategory,Boolean ice,Integer amount) {
        Orders o = new Orders();
        Customer c = new Customer();//사용자 주문 생성
        Goods g = new Goods();
        GoodsLog gl = new GoodsLog();
        List<Customer> customerList;
        if(customerJob == null || customerJob == ""){customerList = this.customerRepository.findByCustomerName(customerName);}
        else {customerList = this.customerRepository.findByCustomerNameAndCustomerJob(customerName,customerJob);}
        Customer customerLastOrder = customerList.get(customerList.size()-1);
        List<Goods> goodsListN = this.goodsRepository.findByGoodsName(goodsName);
        List<Goods> goodsListC = this.goodsRepository.findByGoodsCategory(goodsCategory);
        List<Goods> goodsListI = this.goodsRepository.findByGoodsIce(ice);

        //리스트 교집함
        goodsListN.retainAll(goodsListC);
        goodsListN.retainAll(goodsListI);
        g = goodsListN.get(0);

        g.setGoodsAmount(g.getGoodsAmount()-amount);
        this.goodsLogService.createNewGoodsLog(customerName,-amount,g.getGoodsAmount(),g);

        o.setOrdersDate(LocalDateTime.now());
        o.setOrdersAmount(amount);
        int changedPrice = g.getGoodsPrice()*amount;
        o.setOrdersPrice(g.getGoodsPrice()*amount);

        c.setCustomerId(customerLastOrder.getCustomerId());
        c.setCustomerName(customerName);
        c.setCustomerJob(customerLastOrder.getCustomerJob());
        c.setCustomerDate(LocalDateTime.now());
        c.setCustomerPaymentBalance(customerLastOrder.getCustomerPaymentBalance()-changedPrice);
        c.setChangePaymentBalance(-changedPrice);
        if(LocalDateTime.now().getMonthValue()==customerLastOrder.getCustomerDate().getMonthValue()){
            c.setCustomerMonthlyIn(customerLastOrder.getCustomerMonthlyIn());
            c.setCustomerMonthlyOut(customerLastOrder.getCustomerMonthlyOut()-changedPrice);
        }
        else {
            c.setCustomerMonthlyIn(0);
            c.setCustomerMonthlyOut(-changedPrice);
        }

        o.setGoods(g);
        o.setCustomer(c);
        this.customerRepository.save(c);
        this.ordersRepository.save(o);
    }
    public List<Orders> getPersonalOrderList(Integer id){
        List<Customer> customerList = this.customerRepository.findByCustomerId(id);
        List<Orders> ordersList = this.ordersRepository.findByCustomerCustomerOrderId(customerList.get(0).getCustomerOrderId());
        if(customerList.toArray().length>1) {
            for (int i = 1; i < customerList.toArray().length; i++) {
                ordersList.addAll(this.ordersRepository.findByCustomerCustomerOrderId(customerList.get(i).getCustomerOrderId()));
            }
        }
        if (true){
            return ordersList;
        } else {
            throw new DataNotFoundException("orders not found");
        }
    }

}
