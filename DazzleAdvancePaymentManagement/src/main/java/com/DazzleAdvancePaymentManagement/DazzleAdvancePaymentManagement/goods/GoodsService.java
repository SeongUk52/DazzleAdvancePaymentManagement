package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog.GoodsLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public Page<Goods> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.goodsRepository.findAll(pageable);
    }
    public List<Goods> getList() {
        return this.goodsRepository.findAll();
    }
    public Optional<Goods> getById(int id){
        return this.goodsRepository.findById(id);
    }


    public List<Goods> getGoodsCBB(){
        List<Goods> goodsList = this.goodsRepository.findByGoodsCategory("SIDE_COOKIE");
        goodsList.addAll(this.goodsRepository.findByGoodsCategory("SIDE_BOTTLE"));
        goodsList.addAll(this.goodsRepository.findByGoodsCategory("BAKERY"));

        return goodsList;
    }
    public void delete(Goods goods) {
        this.goodsRepository.delete(goods);
    }
    public Goods getGoods(Integer id) {
        Optional<Goods> goods = this.goodsRepository.findById(id);
        if (goods.isPresent()) {
            return goods.get();
        } else {
            throw new DataNotFoundException("goods not found");
        }
    }
    public List<Goods> getGoodsByCategory(String category) {
        List<Goods> goodsList = this.goodsRepository.findByGoodsCategory(category);
        if (!goodsList.isEmpty()) {
            return goodsList;
        } else {
            throw new DataNotFoundException("goods not found");
        }
    }
    public void createNewGoods(String name, String category, Boolean ice, Integer amount, Integer price) {
        Goods g = new Goods();
        g.setGoodsName(name);
        g.setGoodsCategory(category);
        g.setGoodsIce(ice);
        g.setGoodsAmount(amount);
        g.setGoodsPrice(price);
        g.setGoodsDate(LocalDateTime.now());
        this.goodsRepository.save(g);
    }
    public void modify(Goods goods, String name, String category, Boolean ice, Integer amount, Integer price){
        goods.setGoodsName(name);
        goods.setGoodsCategory(category);
        goods.setGoodsIce(ice);
        goods.setGoodsAmount(amount);
        goods.setGoodsPrice(price);
        goods.setGoodsDate(LocalDateTime.now());
        this.goodsRepository.save(goods);
    }
}
