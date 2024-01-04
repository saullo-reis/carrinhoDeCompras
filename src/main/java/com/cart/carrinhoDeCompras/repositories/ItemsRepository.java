package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Items;
import com.cart.carrinhoDeCompras.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Long> {

}
