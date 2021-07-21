package com.taylormuhrline.moviedb.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.taylormuhrline.moviedb.models.Director;
import com.taylormuhrline.moviedb.repositories.DirectorRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/directors")
public class DirectorController {

	@Autowired
	DirectorRepository directorRepository;
	
	
	
	// ROUTES ----------------------------------------------- //
	
	@GetMapping("/")
	public ResponseEntity<Page<Director>> getAllDirectors(Pageable pageable){
		return ResponseEntity.ok(directorRepository.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Director> getDirectorById(@PathVariable("id")Long id){
		Optional<Director> optionalDirector = directorRepository.findById(id);
		if(!optionalDirector.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(optionalDirector.get());
	}
	
	@PostMapping("/new")
	public ResponseEntity<Director> createDirector(@Valid @RequestBody Director rawDirector){
		
		System.out.println("into createDirector");
		try {
			
			Director savedDirector = directorRepository.save(rawDirector);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		            .buildAndExpand(savedDirector.getId()).toUri();
			return ResponseEntity.created(location).body(savedDirector);
			
		} catch(Exception err) {
			System.out.println(err);
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	@PostMapping("/new/testing")
	public ResponseEntity<Director> createDirectorTesting(@RequestParam("fname")String fname,
				@RequestParam("lname")String lname
			){
		System.out.println("into createDirectorTesting");
		try {
			Director director = new Director(fname, lname);
			Director savedDirector = directorRepository.save(director);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		            .buildAndExpand(savedDirector.getId()).toUri();
			return ResponseEntity.created(location).body(savedDirector);
			
		} catch(Exception err) {
			System.out.println(err);
			return ResponseEntity.unprocessableEntity().build();
		}
	}
}
