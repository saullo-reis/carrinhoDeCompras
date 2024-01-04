package com.cart.carrinhoDeCompras.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CATEGORY_ID;
    private String CATEGORY_NAME;
}
