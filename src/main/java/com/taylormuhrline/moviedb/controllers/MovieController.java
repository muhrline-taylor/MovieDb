package com.taylormuhrline.moviedb.controllers;

import java.net.URI;
import java.util.Optional;
import java.util.Set;

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
import com.taylormuhrline.moviedb.models.Movie;
import com.taylormuhrline.moviedb.repositories.DirectorRepository;
import com.taylormuhrline.moviedb.repositories.MovieRepository;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	DirectorRepository directorRepository;
	
	// ROUTES ----------------------------------- //
	
	@GetMapping("/")
	public ResponseEntity<Page<Movie>> getAllMovies(Pageable pageable){
		return ResponseEntity.ok(movieRepository.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id")Long id){
		Optional<Movie> optionalMovie = movieRepository.findById(id);
		if(!optionalMovie.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(optionalMovie.get());
	}
	
	@PostMapping("/new/{director_id}")
	public ResponseEntity<Movie> createMovie(@RequestBody Movie rawMovie, @PathVariable("director_id")Long director_id){
		
		System.out.println("into createMovie");
		
		Optional<Director> optionalDirector = directorRepository.findById(director_id);
		if(!optionalDirector.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		Director director = optionalDirector.get();
		Set<Movie> directorsMovies = director.getMovies();
		
		Movie newMovie = rawMovie;
		
		directorsMovies.add(newMovie);
		
		newMovie.setDirector(director);
		director.setMovies(directorsMovies);
		System.out.println(director.getMovies());
		
		Movie savedMovie = movieRepository.save(newMovie);
		directorRepository.save(director);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(savedMovie.getId()).toUri();
		return ResponseEntity.created(location).body(savedMovie);		
	}
	
	@PostMapping("/new/testing")
	public ResponseEntity<Movie> createMovieTesting(@RequestParam("name")String name,
				@RequestParam("year")int year, 
				@RequestParam("poster")String poster
			) {
		System.out.println("into createMovieTesting");
		try {
			Movie movie = new Movie(name, year, poster);
			Movie savedMovie = movieRepository.save(movie);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		            .buildAndExpand(savedMovie.getId()).toUri();
			return ResponseEntity.created(location).body(savedMovie);
			
		} catch(Exception err) {
			System.out.println(err);
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
	

}
