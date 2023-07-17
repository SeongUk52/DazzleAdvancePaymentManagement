package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private LocalDateTime orderDate;

    private Integer orderAmount;

    private Integer customerId;

    private Integer goodsId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Goods goods;
}
