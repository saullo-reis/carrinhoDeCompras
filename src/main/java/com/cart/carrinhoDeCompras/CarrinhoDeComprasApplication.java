package com.cart.carrinhoDeCompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class CarrinhoDeComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrinhoDeComprasApplication.class, args);
	}

	@RestController
	class HttpController{
		@GetMapping("/")
		String publicRoute(){
			return "<h1>Página principal</h1>";
		}
	}
}
