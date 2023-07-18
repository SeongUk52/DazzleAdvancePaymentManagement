package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<Customer> getCustomerList(){
        List<Customer> customerList = this.customerRepository.findAll();
        int cusLen = customerList.toArray().length;
        if (cusLen > 1) {
            for (int i = 0; i < customerList.toArray().length; i++) {
                for (int j = 0; j< customerList.toArray().length;j++ ){
                    if (customerList.get(i).getCustomerId() == customerList.get(j).getCustomerId()){
                        if(customerList.get(i).getCustomerOrderId()<customerList.get(j).getCustomerOrderId()){
                            customerList.remove(i);
                            j--;
                        }
                        else if((customerList.get(i).getCustomerOrderId()>customerList.get(j).getCustomerOrderId())){
                            customerList.remove(j);
                            j--;
                        }

                    }
                }
            }
        }

        return customerList;
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
        customer1.setCustomerJob(customer.getCustomerJob());
        this.customerRepository.save(customer1);
    }
    public void createNewCustomer(Integer customerId,String customerName,String customerJob,Integer customerPaymentBalance){
        Customer c = new Customer();
        c.setCustomerId(customerId);
        c.setCustomerName(customerName);
        c.setCustomerJob(customerJob);
        c.setCustomerPaymentBalance(customerPaymentBalance);
        c.setCustomerDate(LocalDateTime.now());
        this.customerRepository.save(c);
    }
}
