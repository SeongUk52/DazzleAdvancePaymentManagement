package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.CustomerRepository;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.GoodsRepository;
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

    public void createNewOrders(String customerName,String goodsName,String goodsCategory,Boolean ice,Integer amount) {
        Orders o = new Orders();
        Customer c = new Customer();//사용자 주문 생성
        Goods g = new Goods();
        List<Customer> customerList = this.customerRepository.findByCustomerName(customerName);
        Customer customerLastOrder = customerList.get(customerList.size()-1);
        List<Goods> goodsListN = this.goodsRepository.findByGoodsName(goodsName);
        List<Goods> goodsListC = this.goodsRepository.findByGoodsCategory(goodsCategory);
        List<Goods> goodsListI = this.goodsRepository.findByGoodsIce(ice);

        //리스트 교집함
        goodsListN.retainAll(goodsListC);
        goodsListN.retainAll(goodsListI);
        g = goodsListN.get(0);

        g.setGoodsAmount(g.getGoodsAmount()-amount);

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

        this.customerRepository.save(c);
        this.ordersRepository.save(o);
    }
}
