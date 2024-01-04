package com.cart.carrinhoDeCompras.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer USER_ID;
    private String USER_NAME;
    private boolean USER_VIP;
    private String USER_EMAIL;
    private String USER_PASSWORD;


    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE")
    private Date UPDATED_DATE;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", updatable = false)
    private Date CREATED_DATE;

    public Users(){

    }
    public Users(String USER_NAME, String USER_EMAIL, String USER_PASSWORD  ){
        this.USER_EMAIL = USER_EMAIL;
        this.USER_NAME = USER_NAME;
        this.USER_VIP = false;
        this.USER_PASSWORD = USER_PASSWORD;
        this.UPDATED_DATE = new Date();
        this.CREATED_DATE = new Date();
    }

    public boolean isUSER_VIP() {
        return USER_VIP;
    }

    public void setUSER_VIP(boolean USER_VIP) {
        this.USER_VIP = USER_VIP;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

}
