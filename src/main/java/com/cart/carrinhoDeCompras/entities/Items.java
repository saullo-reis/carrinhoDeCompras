package com.cart.carrinhoDeCompras.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "ITEMS")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ITEM_ID;
    private String ITEM_NAME;
    private String ITEM_CATEGORY;
    private Date CREATED_DATE;
    private Date UPDATE_DATE;

    @ManyToOne
    @JoinColumn(name="CART_ID")
    private Integer CART_ID;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Integer USER_ID;
    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Integer CATEGORY_ID;


    public Items(String ITEM_NAME, String ITEM_CATEGORY){
        this.ITEM_NAME = ITEM_NAME;
        this.ITEM_CATEGORY = ITEM_CATEGORY;
        this.CREATED_DATE = new Date();
        this.UPDATE_DATE = new Date();
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getITEM_CATEGORY() {
        return ITEM_CATEGORY;
    }

    public void setITEM_CATEGORY(String ITEM_CATEGORY) {
        this.ITEM_CATEGORY = ITEM_CATEGORY;
    }

}
