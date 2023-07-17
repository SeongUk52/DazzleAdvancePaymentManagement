package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.insertOrder;

import java.time.LocalDateTime;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.insertPayment.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.insertStock.Goods;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordersId;

    private LocalDateTime ordersDate;

    private Integer ordersAmount;

    private Integer ordersPrice;

    //private Integer customerId;

    //private Integer goodsId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Goods goods;
}
