package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.dao;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}