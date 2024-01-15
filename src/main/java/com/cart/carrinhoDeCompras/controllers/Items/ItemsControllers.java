package com.cart.carrinhoDeCompras.controllers.Items;

import com.cart.carrinhoDeCompras.entities.Items;
import com.cart.carrinhoDeCompras.entities.Users;
import com.cart.carrinhoDeCompras.repositories.ItemsRepository;
import com.cart.carrinhoDeCompras.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemsControllers {

    @Autowired
    private ItemsRepository itemRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getAll")
    public ResponseEntity<List<Items>> findAllItems(){
        List<Items> result = itemRepository.findAll();
        return ResponseEntity.status(200).body(result);
    }
    @GetMapping("/getOneItemByName/{nameItem}")
    public ResponseEntity<Items> findOneItem(@PathVariable String nameItem){
        Items Item = itemRepository.findByItemName(nameItem);
        return ResponseEntity.status(200).body(Item);
    }

    @PostMapping("/registerItem/{idUser}")
    public ResponseEntity<String> registerOneItem(@RequestBody Items items, @PathVariable Long idUser){
        Optional<Users> user = userRepository.findById(idUser);
        Response response = new Response();

        if(user.isEmpty()){
            response.setMessage("Esse usuário não existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
        try{
            Items result = itemRepository.save(items);
            response.setMessage("Item registrado");
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getMessage());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
