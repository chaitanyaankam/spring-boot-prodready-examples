package com.spring.edm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.edm.entity.Book;

@Repository
public interface AuthorRepository extends JpaRepository<Book, Integer> {
	
	Optional<Book> findByName(String name);
	
	List<Book> findByGenreId(int genreId);
	
	List<Book> findAll();
	
}
