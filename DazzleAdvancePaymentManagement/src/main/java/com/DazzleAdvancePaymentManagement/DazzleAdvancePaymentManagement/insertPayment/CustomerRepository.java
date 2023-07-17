package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.insertPayment;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.insertPayment.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}