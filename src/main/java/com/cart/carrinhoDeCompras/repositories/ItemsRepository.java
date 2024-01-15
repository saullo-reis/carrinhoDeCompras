package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemsRepository extends JpaRepository<Items, Long> {
    public Items findByItemName(String itemName);
}
