package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
    public Items findByItemName(String itemName);
}
