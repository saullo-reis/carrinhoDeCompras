package com.cart.carrinhoDeCompras.repositories;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.cart.carrinhoDeCompras.entities.users.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    UserDetails findByUserName(String userName);
}
