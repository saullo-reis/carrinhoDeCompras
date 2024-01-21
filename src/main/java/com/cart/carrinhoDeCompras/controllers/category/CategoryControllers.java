package com.cart.carrinhoDeCompras.controllers.category;

import com.cart.carrinhoDeCompras.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cart.carrinhoDeCompras.Response;
import com.cart.carrinhoDeCompras.entities.category.Category;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categories")
public class CategoryControllers {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        try{
            List<Category> result = categoryRepository.findAll();
            Response response = new Response<Category>("Sucess", "200", null, result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/findByName/{nameCategory}")
    public ResponseEntity<?> findByName(@PathVariable String nameCategory){
        try{
            List<Category> result = categoryRepository.findByCategoryName(nameCategory);
            Response response = new Response<Category>("Sucess", "200", null, result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @DeleteMapping(value= "/deleteCategory/{idCategory}")
    public ResponseEntity<?> deleleCategory(@PathVariable Integer idCategory){
        try{
            Optional<Category> categoryDeleted = categoryRepository.findById(idCategory);
            categoryRepository.deleteById(idCategory);
            Response response = new Response<Optional<Category>>("Sucess", "200", categoryDeleted, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value="/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        try{
            Category saveCategory = categoryRepository.save(category);
            Response response = new Response<Category>("Sucess", "200", saveCategory, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}