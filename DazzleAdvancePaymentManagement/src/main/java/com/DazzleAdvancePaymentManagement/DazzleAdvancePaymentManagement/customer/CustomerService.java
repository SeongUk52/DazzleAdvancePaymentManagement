package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getList(){
        return this.customerRepository.findAll();
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
    public void create(Customer customer, Integer changePaymentBalance){
        Customer customer1 = new Customer();
        customer1.setCustomerId(customer.getCustomerId());
        customer1.setCustomerName(customer.getCustomerName());
        customer1.setCustomerPaymentBalance(customer.getCustomerPaymentBalance()+changePaymentBalance);
        customer1.setCustomerDate(LocalDateTime.now());
        customer1.setStore(customer.getStore());
        customer1.setChangePaymentBalance(changePaymentBalance);
        this.customerRepository.save(customer1);
    }
}