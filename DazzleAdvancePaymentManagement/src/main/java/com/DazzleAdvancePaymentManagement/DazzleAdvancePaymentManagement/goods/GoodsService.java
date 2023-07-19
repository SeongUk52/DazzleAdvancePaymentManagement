package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public Page<Goods> getList(int page) {
        Pageable pageable = PageRequest.of(page, 15);
        return this.goodsRepository.findAll(pageable);
    }
}
