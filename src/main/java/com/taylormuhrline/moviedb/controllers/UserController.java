package com.taylormuhrline.moviedb.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taylormuhrline.moviedb.models.User;
import com.taylormuhrline.moviedb.services.UserService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	
	// ROUTES ================================== //
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id")Long id){
		return userService.getUserById(id);
	}
	
	@PostMapping("/new")
	public ResponseEntity<User> createUser(@RequestBody Map<String, Object> payload){
		
		System.out.println("into createUser");
		System.out.println(payload);
		System.out.println(payload.get("username").toString());
		
		
		
		try {
			ResponseEntity<User> newUser = userService.createUser(
						payload.get("email").toString(),
						payload.get("username").toString(),
						payload.get("password").toString(),
						payload.get("confirm_pw").toString()
						
					);
			return newUser;
			
		}catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.unprocessableEntity().build();
		}
		
	}
	
	@GetMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam("email")String email, @RequestParam("password")String password){
		
		System.out.println("into loginUser");
		
		try {
			
			return userService.loginUser(email, password);
			
			
			
		} catch(Exception e) {
			System.out.println(e);
			return ResponseEntity.unprocessableEntity().build();
		}
		
		
		
		
	}
	
	
	
	
	

}
