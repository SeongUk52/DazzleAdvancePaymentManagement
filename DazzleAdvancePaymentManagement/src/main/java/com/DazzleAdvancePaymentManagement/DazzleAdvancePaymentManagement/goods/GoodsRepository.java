package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    Page<Goods> findAll(Pageable pageable);
}