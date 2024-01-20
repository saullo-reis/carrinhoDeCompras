package com.cart.carrinhoDeCompras.controllers.users;

import com.cart.carrinhoDeCompras.entities.Users;
import com.cart.carrinhoDeCompras.repositories.UserRepository;
import com.cart.carrinhoDeCompras.Response;
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

    @PostMapping(value = "/register")
    public ResponseEntity<?> insert(@RequestBody Users user) {
        try{
            user.setUserPassword(encoder.encode(user.getUserPassword()));
            Users result = repository.save(user);
            Response response = new Response<Users>("Sucess", "200", result, null );
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/login/{login}/{password}")
    public ResponseEntity<Boolean> validate(@PathVariable String login, @PathVariable String password){
        try{
            Optional<Users> optUser = repository.findByUserName(login);
            if(optUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }

            Users usuario = optUser.get();
            boolean valid = false;
            valid = encoder.matches(password, usuario.getUserPassword());

            HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

            return ResponseEntity.status(status).body(valid);
        }catch(Exception e){
            return ResponseEntity.status(500).body(false);
        }
    }
}
