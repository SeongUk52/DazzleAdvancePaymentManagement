package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.model;

import java.time.LocalDateTime;
import java.util.List;

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
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;

    private String storeName;

    private Integer storePaymentBalance;

    private LocalDateTime storeDate;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Customer> customerList;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Goods> goodsList;
}
