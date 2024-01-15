package com.cart.carrinhoDeCompras.controllers.cart;

import com.cart.carrinhoDeCompras.entities.Carts;
import com.cart.carrinhoDeCompras.entities.Users;
import com.cart.carrinhoDeCompras.repositories.CartsRepository;
import com.cart.carrinhoDeCompras.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/carts")
public class CartControllers {

    @Autowired
    private CartsRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value="/getAll")
    public ResponseEntity<?> findAllCarts(){
        try{
            List<Carts> result = cartRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value="/getCart/{id}")
    public ResponseEntity<?> findOneCart(@PathVariable Long id){
        try{
            Optional<Carts> result = cartRepository.findById(id);
            if(result.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body("Não foi encontrado nenhum carrinho com esse id");
            }
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value="/getCarts/userId/{userId}")
    public ResponseEntity<?> findCartsByUserId(@PathVariable Long userId){
        try{
            Optional<Users> user = userRepository.findById(userId);
            if(user.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User não encontrado");
            }
            List<Carts> responseGetCarts = cartRepository.findByUserId_UserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetCarts);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
