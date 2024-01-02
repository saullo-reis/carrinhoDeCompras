package com.cart.carrinhoDeCompras.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CATEGORY_ID;
    private String CATEGORY_NAME;
}
