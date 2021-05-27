package com.spring.edm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.edm.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
		
}
