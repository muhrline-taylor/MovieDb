package com.taylormuhrline.moviedb.services;

import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taylormuhrline.moviedb.models.User;
import com.taylormuhrline.moviedb.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// GET ALL
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	// GET BY ID
	public User getUserById(Long user_id) {
		return userRepository.findById(user_id).orElse(null);
	}
	
	// CREATE
	public ResponseEntity<User> createUser(String email, String username, String password, String confirm_pw) {
		
		System.out.println("into UserService.createUser()");
		
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if(optionalUser.isPresent()) {
			System.out.println("Email already registered!");
			return ResponseEntity.unprocessableEntity().build();
		}
		
		
		boolean isEmailValid = EmailValidator.getInstance().isValid(email);
		boolean arePwValid = password.equals(confirm_pw);
		System.out.println(arePwValid);
		
		if(isEmailValid == true && arePwValid == true) {
			System.out.println("email is valid");
			try {
				User newUser = new User(
						email,
						username,
						password
					);
				userRepository.save(newUser);
				return ResponseEntity.ok(newUser);
				
			} catch(Exception e) {
				System.out.println(e);
				return ResponseEntity.unprocessableEntity().build();
			}
			
		} else {
			System.out.println("email is not valid");
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	// LOGIN
	public ResponseEntity<User> loginUser(String email, String password){
		
		System.out.println("into UserService.loginUser");
		
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if(!optionalUser.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		User user = optionalUser.get();
		
		if(password.equals(user.getPassword())) {
			System.out.println("correct password");
			return ResponseEntity.ok(user);
		} else {
			System.out.println("incorrect password");
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	// DELETE
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
