package com.cart.carrinhoDeCompras.controllers.users;

import com.cart.carrinhoDeCompras.entities.Users;
import com.cart.carrinhoDeCompras.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllers {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<Users> findAll(){
        List<Users> result = repository.findAll();
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/register")
    public Users insert(@RequestBody Users user) {
        Users result = repository.save(user);
        return result;
    }
}
