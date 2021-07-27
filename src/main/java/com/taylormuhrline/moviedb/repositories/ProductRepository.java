package com.taylormuhrline.moviedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taylormuhrline.moviedb.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	

}
