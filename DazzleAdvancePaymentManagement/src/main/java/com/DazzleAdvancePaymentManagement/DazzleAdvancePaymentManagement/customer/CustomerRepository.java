package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCustomerName(String customerName);
    List<Customer> findByCustomerId(Integer customerId);
    List<Customer> findByCustomerNameAndCustomerJob(String customerName,String customerJob);
    Page<Customer> findAll(Pageable pageable);
    List<Customer> findByCustomerIdAndCustomerDateBetween(Integer customerId, LocalDateTime fromDate, LocalDateTime toDate);

}