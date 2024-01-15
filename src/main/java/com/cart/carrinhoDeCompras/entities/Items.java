package com.cart.carrinhoDeCompras.entities;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.util.Date;

@Entity
@Table (name = "ITEMS")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String itemName;
    private Date createdDate;
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name="cartId")
    private Carts cartId;
    @ManyToOne
    @JoinColumn(name="userId")
    private Users userId;
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category categoryId;

    public Items(){

    }
    public Items(String itemName, String itemCategory){
        this.itemName = itemName;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

}
