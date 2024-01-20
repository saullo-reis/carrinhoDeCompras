package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT u FROM Category u WHERE u.categoryName LIKE CONCAT('%', :name, '%')")
    List<Category> findByCategoryName(String name);
}
