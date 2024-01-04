package com.cart.carrinhoDeCompras.controllers.users;

import com.cart.carrinhoDeCompras.entities.Users;
import com.cart.carrinhoDeCompras.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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
    public List<Users> findAll(){
        List<Users> result = repository.findAll();
        return result;
    }

    @CrossOrigin
    @PostMapping(value = "/register")
    public Users insert(@RequestBody Users user) {
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        Users result = repository.save(user);
        return result;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Boolean> validate(@RequestParam String login,
                                            @RequestParam String password){
        Optional<Users> optUser = repository.findByUserName(login);
        if(optUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        Users usuario = optUser.get();
        boolean valid = false;
        valid = encoder.matches(password, usuario.getUserPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }
}
