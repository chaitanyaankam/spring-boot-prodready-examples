package com.spring.edm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.edm.entity.Book;
import com.spring.edm.exception.ResourceNotFoundException;
import com.spring.edm.service.BookService;
import com.spring.edm.util.ResponseConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins="http://localhost:4200", maxAge = 4800)
@RestController
@Api(value="onlinestore", description="Operations pertaining to books in Books Store")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(value="/api/books/{name}")
	@ApiOperation(value = "View a list of available books with given name")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public Book getAllBook(@PathVariable String name){
		Optional<Book> optionalBook = bookService.findByName(name);
		return optionalBook.orElseThrow(ResourceNotFoundException::new);
	}

	@ApiOperation(value = "View a list of available books")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	@GetMapping(value="/api/books")
	public ResponseEntity<?> getAllBooks() throws Exception {
		Optional<List<Book>> optionalBooks = Optional.of(bookService.findAllBooks());
		return new ResponseEntity<>(optionalBooks.get(), HttpStatus.OK);
	}

	@PostMapping(value="/api/books", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Save a book records")
	public ResponseEntity<String> saveBook(@RequestBody Book book){
		Optional<Book> optionalBook = bookService.findByName(book.getName());
		if(optionalBook.isPresent())
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(ResponseConstants.SUCCSESS_MESSAGE);
		bookService.saveBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseConstants.SUCCSESS_MESSAGE);
	}
}
