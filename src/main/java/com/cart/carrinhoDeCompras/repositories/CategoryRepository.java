package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
