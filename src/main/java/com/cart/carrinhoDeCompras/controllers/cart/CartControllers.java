package com.cart.carrinhoDeCompras.controllers.cart;

import com.cart.carrinhoDeCompras.Response;
import com.cart.carrinhoDeCompras.entities.carts.Carts;
import com.cart.carrinhoDeCompras.entities.users.Users;
import com.cart.carrinhoDeCompras.repositories.CartsRepository;
import com.cart.carrinhoDeCompras.repositories.UserRepository;

import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            List<Carts> allCarts = cartRepository.findAll();
            Response response = new Response<Carts>("SUCESS", "200", null, allCarts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value="/getCart/{id}")
    public ResponseEntity<?> findOneCart(@PathVariable Integer id){
        try{
            Optional<Carts> cartById = cartRepository.findById(id);
            if(cartById.isEmpty()){
                Response response = new Response<Carts>("NOT FOUND CART", "400", null, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            Response response = new Response<Optional<Carts>>("SUCESS", "200", cartById, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable Integer cartId){
        try{
            Optional<Carts> cart = cartRepository.findById(cartId);
            if(cart.isEmpty()){
                Response response = new Response<Carts>("CART DONT FOUND", "400", null, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            cartRepository.deleteById(cartId);
            Response response = new Response<Optional<Carts>>("CART DELETED", "200", cart, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/getCarts/userId/{userId}")
    public ResponseEntity<?> findCartsByUserId(@PathVariable Integer userId){
        try{
            Optional<Users> user = userRepository.findById(userId);
            if(user.isEmpty()){
                Response response = new Response<Carts>("USER NOT FOUND", "400", null, null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            List<Carts> cartsByUserId = cartRepository.findByUserId(userId);
            Response response = new Response<Carts>("SUCESS", "200", null, cartsByUserId);
            return new ResponseEntity<> (response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/registerCart")
    public ResponseEntity<?> registerCart(@RequestBody Carts cart){
        try{
            Carts cartRegistered = cartRepository.save(cart);
            Response response = new Response<Carts>("CART REGISTERED", "200", cartRegistered, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
