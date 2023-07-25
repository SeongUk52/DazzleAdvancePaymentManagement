package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog.GoodsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    List<Goods> findByGoodsName(String goodsName);
    List<Goods> findByGoodsCategory(String goodsCategory);
    List<Goods> findByGoodsCategoryOrGoodsCategoryOrGoodsCategory(String goodsCategory1,String goodsCategory2,String goodsCategory3);
    List<Goods> findByGoodsIce(Boolean goodsIce);
    Goods findByGoodsNameAndGoodsCategoryAndGoodsIce(String goodsName,String goodsCategory, Boolean goodsIce);

    Optional<Goods> findByGoodsId(Integer goodsId);
    List<Goods> findAll();
    Page<Goods> findAll(Pageable pageable);
}