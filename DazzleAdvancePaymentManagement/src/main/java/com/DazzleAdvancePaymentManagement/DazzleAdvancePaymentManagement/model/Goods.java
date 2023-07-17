package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodsId;

    private String goodsName;

    private String goodsCategory;

    private Boolean goodsIce;

    private Integer goodsAmount;

    private Integer goodsPrice;

    private LocalDateTime goodsDate;

    private Integer storeId;

    private Integer storeAmount;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.REMOVE)
    private List<Order> orderList;
}
