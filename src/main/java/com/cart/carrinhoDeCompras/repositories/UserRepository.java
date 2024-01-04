package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

}
