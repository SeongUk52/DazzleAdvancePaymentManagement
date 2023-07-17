package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.dao;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}