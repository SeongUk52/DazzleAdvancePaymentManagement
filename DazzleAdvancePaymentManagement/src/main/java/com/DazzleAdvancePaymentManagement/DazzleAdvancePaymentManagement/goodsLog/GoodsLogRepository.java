package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsLogRepository  extends JpaRepository<GoodsLog, Integer> {
    List<GoodsLog> findByGoods(Goods goods);
    Page<GoodsLog> findAll(Pageable pageable);
}
