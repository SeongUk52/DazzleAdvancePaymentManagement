package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.insertPayment;

import java.time.LocalDateTime;
import java.util.List;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.insertOrder.Orders;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.Store;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;

    private String customerJob;

    private Integer customerPaymentBalance;

    private LocalDateTime customerDate;

    //private Integer storeId;

    private Integer changePaymentBalance;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Orders> ordersList;
}
