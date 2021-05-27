package com.mkyong;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.book.model.Book;
import com.mkyong.book.service.BookServiceImpl;

@RestController
@RequestMapping("/book/elastic")
public class ElasticController {

	@Autowired
	private SearchQueryBuilder searchQueryBuilder;

	@Autowired
	private BookServiceImpl bookService;

	@PostMapping("/")
	public ResponseEntity<?> saveBook(@RequestBody Book book) throws Exception{
		Book bookSaved = bookService.save(book);
		return ResponseEntity.ok(bookSaved);
	}

	@GetMapping(value = "/{text}")
	public List<Book> getAll(@PathVariable final String text) {
		try {
			return searchQueryBuilder.getAll(text);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}