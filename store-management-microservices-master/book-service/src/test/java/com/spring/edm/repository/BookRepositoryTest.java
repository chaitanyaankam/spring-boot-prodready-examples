package com.spring.edm.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.edm.entity.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void whenFindByName_thenReturnBook() {
	    Book testBook = Book.builder().name("Test Book").cost(2).build();//given
		entityManager.persist(testBook);
	    entityManager.flush();	
	    
	    Optional<Book> found = bookRepository.findByName(testBook.getName());//when	 
	    assertThat(found.get().getName()).isEqualTo(testBook.getName());//then
	}
}
