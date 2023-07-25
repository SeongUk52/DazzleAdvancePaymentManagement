package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GoodsLogRepository  extends JpaRepository<GoodsLog, Integer> {
    List<GoodsLog> findByGoodsGoodsId(Integer goodsGoodsId);

    List<GoodsLog> findByGoodsGoodsCategoryAndGoodsLogTimeBetween(String goodsGoodsCategory, LocalDateTime fromDate, LocalDateTime toDate);

    Page<GoodsLog> findAll(Pageable pageable);
}
