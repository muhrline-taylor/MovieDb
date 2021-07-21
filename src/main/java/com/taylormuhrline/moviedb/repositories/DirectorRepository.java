package com.taylormuhrline.moviedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylormuhrline.moviedb.models.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>{

}
