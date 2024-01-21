package com.cart.carrinhoDeCompras.controllers.authenticator;

import com.cart.carrinhoDeCompras.Response;
import com.cart.carrinhoDeCompras.entities.users.*;
import com.cart.carrinhoDeCompras.repositories.UserRepository;
import com.cart.carrinhoDeCompras.services.TokenService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticatorController{

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
       var usernamePassword =
               new UsernamePasswordAuthenticationToken(data.login(), data.password());
       var auth =
               this.authenticationManager.authenticate(usernamePassword);
       var token =
               tokenService.generateToken((Users) auth.getPrincipal());
       return new ResponseEntity<>(new ResponseTokenDTO(token),
               HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> insert(@RequestBody RegisterDTO user) {
        if(this.repository.findByUserName(user.login()) != null){
            Response response = new Response(
                    "USER ALREADY REGISTERED",
                    "400", null, null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            String encryptedPassword =
                    new BCryptPasswordEncoder().encode(user.password());
            Users newUser = new Users(
                    user.role(),
                    user.login(),
                    user.email(),
                    encryptedPassword);
            this.repository.save(newUser);
            Response response =
                    new Response<Users>("Sucess", "200", newUser, null );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}