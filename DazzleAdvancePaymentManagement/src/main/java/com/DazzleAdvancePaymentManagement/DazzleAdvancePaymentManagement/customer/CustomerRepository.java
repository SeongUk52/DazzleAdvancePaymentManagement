package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCustomerId(Integer customerId);
    Page<Customer> findAll(Pageable pageable);

}