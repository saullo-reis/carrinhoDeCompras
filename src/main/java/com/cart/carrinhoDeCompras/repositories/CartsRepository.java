package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CartsRepository extends JpaRepository<Carts, Long> {
        List<Carts> findByUserId_UserId(Long userId);
}
