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

import com.taylormuhrline.moviedb.models.Actor;
import com.taylormuhrline.moviedb.repositories.ActorRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/actors")
public class ActorController {
	
	@Autowired
	ActorRepository actorRepository;
	
	// ROUTES ----------------------------------------- // 
	
	@GetMapping("/")
	public ResponseEntity<Page<Actor>> getAllActors(Pageable pageable){
		return ResponseEntity.ok(actorRepository.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Actor> getActorById(@PathVariable("id")Long id){
		Optional<Actor> optionalActor = actorRepository.findById(id);
		if(!optionalActor.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(optionalActor.get());
	}
	
	@PostMapping("/new")
	public ResponseEntity<Actor> createActor(@Valid @RequestBody Actor rawActor){
		
		System.out.println("into createActor");
		try {
			Actor savedActor = actorRepository.save(rawActor);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		            .buildAndExpand(savedActor.getId()).toUri();
			return ResponseEntity.created(location).body(savedActor);
			
		} catch(Exception err) {
			System.out.println(err);
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	@PostMapping("/new/testing")
	public ResponseEntity<Actor> createActorTesting(@RequestParam("fname")String fname, @RequestParam("lname")String lname){
		System.out.println("into createActorTesting");
		try {
			Actor actor = new Actor(fname, lname);
			Actor savedActor = actorRepository.save(actor);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		            .buildAndExpand(savedActor.getId()).toUri();
			return ResponseEntity.created(location).body(savedActor);
			
		} catch(Exception err) {
			System.out.println(err);
			return ResponseEntity.unprocessableEntity().build();
		}
	}

}
