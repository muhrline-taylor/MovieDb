package com.taylormuhrline.moviedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylormuhrline.moviedb.models.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>{

}
