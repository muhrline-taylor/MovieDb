package com.taylormuhrline.moviedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylormuhrline.moviedb.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

}
