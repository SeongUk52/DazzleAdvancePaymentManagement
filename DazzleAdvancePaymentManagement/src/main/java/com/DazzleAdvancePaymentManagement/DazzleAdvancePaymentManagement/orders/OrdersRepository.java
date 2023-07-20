package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByCustomerCustomerOrderId(Integer customerCustomerOrderId);
}