package com.cart.carrinhoDeCompras.controllers.category;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.cart.carrinhoDeCompras.entities.Category;
import com.cart.carrinhoDeCompras.repositories.CategoryRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryControllers {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        try{
            List<Category> result = categoryRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
