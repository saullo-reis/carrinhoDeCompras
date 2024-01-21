package com.cart.carrinhoDeCompras.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.carrinhoDeCompras.entities.items.Items;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
    public Items findByItemName(String itemName);
}
