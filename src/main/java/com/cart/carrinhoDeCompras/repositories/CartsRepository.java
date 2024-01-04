package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsRepository extends JpaRepository<Carts, Long> {
}
