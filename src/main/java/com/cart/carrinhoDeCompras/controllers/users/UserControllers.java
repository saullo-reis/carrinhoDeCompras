package com.cart.carrinhoDeCompras.controllers.users;

import com.cart.carrinhoDeCompras.repositories.UserRepository;
import com.cart.carrinhoDeCompras.Response;
import com.cart.carrinhoDeCompras.entities.users.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserControllers {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<?> findAll(){
        try{
            List<Users> result = repository.findAll();
            Response response = new Response<Users>("Sucess", "200", null, result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        
    }

    @GetMapping("/findOne/{idUser}")
    public ResponseEntity<?> findOneUser(@PathVariable Integer idUser){
        try{
            Optional<Users> result = repository.findById(idUser);
            Response response = new Response<Optional<Users>>("Sucess", "200", result, null );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
        
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
        try{
            Optional<Users> user = repository.findById(userId);
            repository.deleteById(userId);
            Response response = new Response<Optional<Users>>("USER DELETED", "200", user, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
