package com.spring.edm.service;

import java.util.List;
import java.util.Optional;

import com.spring.edm.entity.Book;

public interface BookService {
	
	Optional<Book> findByName(String name);
	
	List<Book> findAllBooks() throws Exception;
	
	Book saveBook(Book book);
}
