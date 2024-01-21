package com.cart.carrinhoDeCompras.entities.users;

public record RegisterDTO(String login,
                          String password,
                          UserRole role,
                          String email,
                          boolean isVip ) {
}
