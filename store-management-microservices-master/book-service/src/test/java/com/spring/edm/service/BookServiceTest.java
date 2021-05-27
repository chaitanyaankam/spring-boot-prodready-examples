package com.spring.edm.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.edm.entity.Book;
import com.spring.edm.repository.BookRepository;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	
	private static final String TEST_BOOK_NAME = "Test Book";

	@TestConfiguration
    static class BookServiceImplTestContextConfiguration {
  
        @Bean
        public BookService employeeService() {
            return new BookServiceImpl();
        }
    }
	
	@Autowired
    private BookService bookService;
 
    @MockBean
    private BookRepository bookRepository;
    
    @Before
    public void setUp() {
        Book testBook = Book.builder().id(1).name(TEST_BOOK_NAME).cost(2).build();     
        Mockito.when(bookRepository.findByName(testBook.getName()))
          .thenReturn(Optional.of(testBook));
    }
	
    @Test
    public void whenValidName_thenBookShouldBeFound() {
        String name = TEST_BOOK_NAME;
        Optional<Book> found = bookService.findByName(name);      
         assertThat(found.get().getName()).isEqualTo(name);
     }
}
