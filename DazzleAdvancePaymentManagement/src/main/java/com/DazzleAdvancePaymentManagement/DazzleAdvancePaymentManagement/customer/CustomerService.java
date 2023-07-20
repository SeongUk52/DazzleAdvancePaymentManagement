package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getList(){
        return this.customerRepository.findAll();
    }
    public Page<Customer> getPageList(int page){
        Pageable pageable = PageRequest.of(page,15);
        return this.customerRepository.findAll(pageable);
    }
    public Page<Customer> getCustomerList(int page){
        List<Customer> customerList = this.customerRepository.findAll();
        Pageable pageable = PageRequest.of(page,15);
        // 페이지 기능 구현 필요
        int cusLen = customerList.toArray().length;
        if (cusLen > 1) {
            for (int i = 0; i < customerList.toArray().length; i++) {
                for (int j = i+1; j< customerList.toArray().length;j++ ){
                    if (customerList.get(i).getCustomerId() == customerList.get(j).getCustomerId()){
                        if(customerList.get(i).getCustomerOrderId()<customerList.get(j).getCustomerOrderId()){
                            customerList.remove(i);
                            j--;
                            i--;
                            break;
                        }
                        else if((customerList.get(i).getCustomerOrderId()>customerList.get(j).getCustomerOrderId())){
                            customerList.remove(j);
                            j--;
                        }
                    }
                    else if(customerList.get(i).getCustomerId() > customerList.get(j).getCustomerId()){
                        Collections.swap(customerList, i, j);
                    }
                }
            }
        }

        //이전달 기록들 0으로 "표기" (값변경 x)
        for (int i = 0; i < customerList.toArray().length; i++) {
            if(customerList.get(i).getCustomerDate().getYear() <= LocalDateTime.now().getYear()){
                if(customerList.get(i).getCustomerDate().getMonthValue()<LocalDateTime.now().getMonthValue()){
                    customerList.get(i).setCustomerMonthlyIn(0);
                    customerList.get(i).setCustomerMonthlyOut(0);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), customerList.size());
        Page<Customer> customerPage = new PageImpl<>(customerList.subList(start, end), pageable, customerList.size());
        return customerPage;
    }

    public List<Customer> getPersonalList(Integer id){
        List<Customer> customer = this.customerRepository.findByCustomerId(id);
        if (true){
            return customer;
        } else {
            throw new DataNotFoundException("customer not found");
        }
    }
    public Customer getCustomer(Integer id){
        Optional<Customer> customer = this.customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }else {
            throw new DataNotFoundException("customer not found");
        }
    }

    //고객 선수금 입력(결재)
    public void create(Customer customer, Integer changePaymentBalance){
        //customer는 고객의 가장 최근 기록임
        Customer customer1 = new Customer();
        customer1.setCustomerId(customer.getCustomerId());
        customer1.setCustomerName(customer.getCustomerName());
        customer1.setCustomerPaymentBalance(customer.getCustomerPaymentBalance()+changePaymentBalance);
        customer1.setCustomerDate(LocalDateTime.now());
        customer1.setStore(customer.getStore());
        customer1.setChangePaymentBalance(changePaymentBalance);
        customer1.setCustomerJob(customer.getCustomerJob());
        customer1.setCustomerMonthlyOut(customer.getCustomerMonthlyOut());
        if(LocalDateTime.now().getMonthValue()==customer.getCustomerDate().getMonthValue()){
            customer1.setCustomerMonthlyIn(customer.getCustomerMonthlyIn()+changePaymentBalance);
        }
        else {
            customer1.setCustomerMonthlyIn(changePaymentBalance);
        }
        this.customerRepository.save(customer1);
    }
    public void createNewCustomer(Integer customerId,String customerName,String customerJob,Integer customerPaymentBalance){
        Customer c = new Customer();
        c.setCustomerId(customerId);
        c.setCustomerName(customerName);
        c.setCustomerJob(customerJob);
        c.setCustomerPaymentBalance(customerPaymentBalance);
        c.setCustomerMonthlyIn(0);
        c.setCustomerMonthlyOut(0);
        c.setCustomerDate(LocalDateTime.now());
        this.customerRepository.save(c);
    }

}
