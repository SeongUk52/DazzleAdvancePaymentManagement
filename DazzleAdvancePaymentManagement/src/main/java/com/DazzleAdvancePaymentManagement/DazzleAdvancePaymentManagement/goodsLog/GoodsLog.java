package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog;


import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.Store;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class GoodsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodsLogId;

    private Integer goodsLogChange;

    private Integer goodsLogNow;

    private LocalDateTime goodsLogTime;

    @ManyToOne
    private Goods goods;
}
