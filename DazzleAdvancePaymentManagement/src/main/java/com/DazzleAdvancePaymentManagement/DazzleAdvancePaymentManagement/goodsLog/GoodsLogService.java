package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog;


import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders.Orders;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsLogService {
    private final GoodsLogRepository goodsLogRepository;

    public List<GoodsLog> getGoodsLogList(Integer id){
        List<GoodsLog> goodsLogList = this.goodsLogRepository.findByGoodsGoodsId(id);
        if (true){
            return goodsLogList;
        } else {
            throw new DataNotFoundException("logs not found");
        }
    }
    public void createNewGoodsLog(String goodsLogWho, Integer goodsLogChange, Integer goodsLogNow, Goods goods) {
        GoodsLog gl = new GoodsLog();
        gl.setGoodsLogWho(goodsLogWho);
        gl.setGoodsLogChange(goodsLogChange);
        gl.setGoodsLogNow(goodsLogNow);
        gl.setGoodsLogTime(LocalDateTime.now());
        gl.setGoods(goods);
        this.goodsLogRepository.save(gl);

    }
}
