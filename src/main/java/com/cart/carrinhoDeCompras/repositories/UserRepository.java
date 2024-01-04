package com.cart.carrinhoDeCompras.repositories;

import com.cart.carrinhoDeCompras.entities.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByUserName(String userName);
}
