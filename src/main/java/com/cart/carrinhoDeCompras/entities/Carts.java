package com.cart.carrinhoDeCompras.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CARTS")
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CART_ID;
    private String CART_NAME;
    private Date CREATED_DATE;
    private Date UPDATED_DATE;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Integer USER_ID;

    public Carts(String CART_NAME){
        this.CART_NAME = CART_NAME;
        this.CREATED_DATE = new Date();
        this.UPDATED_DATE = new Date();
    }

    public String getCART_NAME() {
        return CART_NAME;
    }

    public void setCART_NAME(String CART_NAME) {
        this.CART_NAME = CART_NAME;
    }
}
