package com.cart.carrinhoDeCompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.carrinhoDeCompras.entities.carts.Carts;

import java.util.List;


public interface CartsRepository extends JpaRepository<Carts, Integer> {
        List<Carts> findByUserId(Integer userId);
}
