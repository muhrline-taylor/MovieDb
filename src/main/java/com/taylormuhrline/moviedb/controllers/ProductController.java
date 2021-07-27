package com.taylormuhrline.moviedb.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taylormuhrline.moviedb.models.Product;
import com.taylormuhrline.moviedb.models.User;
import com.taylormuhrline.moviedb.services.ProductService;
import com.taylormuhrline.moviedb.services.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/store/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	
	
	// ROUTES ================================================ //
	
	@GetMapping("/")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id")Long id) {
		return productService.getProductById(id);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Product> createProduct(@RequestBody Map<String, Object> payload){
		
		System.out.println("into createProduct");
		
		System.out.println(payload.get("price").toString());
		
		BigDecimal newPrice = new BigDecimal(payload.get("price").toString());
		
		System.out.println(newPrice);
		
		productService.createProduct(
				payload.get("name").toString(), 
				newPrice, 
				Long.valueOf(payload.get("seller_id").toString())
			);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/buy")
	public void buyProduct(@RequestBody Map<String, Object> payload) {
		
		System.out.println("into buyProduct");
		
		System.out.println(payload.get("product_id"));
		System.out.println(payload.get("user_id"));
		
		Product product = productService.getProductById(Long.parseLong(payload.get("product_id").toString()));
		User user = userService.getUserById(Long.parseLong(payload.get("user_id").toString()));
		
		productService.buyProduct(product, user);
		
	}
	
	
	
}
