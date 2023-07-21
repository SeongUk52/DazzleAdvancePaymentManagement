package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    List<Goods> findByGoodsName(String goodsName);
    List<Goods> findByGoodsCategory(String goodsCategory);
    List<Goods> findByGoodsIce(Boolean goodsIce);

    Optional<Goods> findByGoodsId(Integer goodsId);
    List<Goods> findAll();
    Page<Goods> findAll(Pageable pageable);
}