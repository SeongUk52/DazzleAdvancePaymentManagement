package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.dao;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}