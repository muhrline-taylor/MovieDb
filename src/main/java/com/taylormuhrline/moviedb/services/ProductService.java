package com.taylormuhrline.moviedb.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taylormuhrline.moviedb.models.Product;
import com.taylormuhrline.moviedb.models.User;
import com.taylormuhrline.moviedb.repositories.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	@Autowired
	UserService userService;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	// GET ALL
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	// GET BY ID
	public Product getProductById(Long product_id) {
		return productRepository.findById(product_id).orElse(null);
	}
	
	// CREATE
	public ResponseEntity<Product> createProduct(String name, BigDecimal price, Long seller_id) {
		
		System.out.println("into ProductService.createProduct");
		User seller = userService.getUserById(seller_id);
		
		try {
			Product newProduct = new Product(
						name,
						price,
						seller
					);
			productRepository.save(newProduct);
			
			return ResponseEntity.ok(newProduct);
	
		} catch(Exception e) {
			System.out.println(e);
			
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	// SELL PRODUCT
	public ResponseEntity<Product> buyProduct(Product rawProduct, User rawUser){
		// get buyerById
		// get productById
		
		// product.addBuyer(buyer)
		// repo.save(product)
		
		System.out.println("into productService.buyProduct");
		
		rawProduct.setBuyer(rawUser);
		
		productRepository.save(rawProduct);
		
		
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
