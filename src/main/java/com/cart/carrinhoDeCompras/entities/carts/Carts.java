package com.cart.carrinhoDeCompras.entities.carts;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "CARTS")
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private String cartName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    @Column(unique = true, nullable = false)
    private Integer userId;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
