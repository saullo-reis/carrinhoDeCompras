package com.cart.carrinhoDeCompras.controllers.Items;

import com.cart.carrinhoDeCompras.Response;
import com.cart.carrinhoDeCompras.entities.items.Items;
import com.cart.carrinhoDeCompras.entities.users.Users;
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
    public ResponseEntity<?> findAllItems(){
        try{
            List<Items> allItems = itemRepository.findAll();
            Response response = new Response<Items>("Sucess", "200", null, allItems);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getOneItemByName/{nameItem}")
    public ResponseEntity<?> findOneItem(@PathVariable String nameItem){
        try{
            Items item = itemRepository.findByItemName(nameItem);
            if(item == null){
                Response response = new Response<Items>(""+nameItem+" NOT FOUND", "400", item, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            Response response = new Response<Items>(""+nameItem+" FOUND", "200", item, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.
            INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteItem/{idItem}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer idItem){
        try{
            Optional<Items> itemDeleted = itemRepository.findById(idItem);
            if(itemDeleted.isEmpty()){
                Response response = new Response<Items>("ITEM ID: "+idItem+" NOT FOUND", "400", null, null);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            Response response = new Response<Optional<Items>>("ITEM DELETED", "200", null, null);
            itemRepository.deleteById(idItem);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/registerItem/{idUser}")
    public ResponseEntity<?> registerOneItem(@RequestBody Items items, @PathVariable Integer idUser){
        Optional<Users> user = userRepository.findById(idUser);

        if(user.isEmpty()){
            Response response = new Response<Items>("USER NOT FOUND", "200", items, null);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        try{ 
            Items itemResponse = new Items(items.getItemName(), items.getCategoryId(), items.getUserId(), items.getCartId());
            Items result = itemRepository.save(itemResponse);
            Response response = new Response<Items>("USER REGISTERED", "200", result, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
