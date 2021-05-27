package com.spring.edm.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.edm.entity.Book;
import com.spring.edm.service.BookService;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private BookService bookService;
    
    @Test
    public void givenBooks_whenGetBooks_thenReturnJsonArray() throws Exception {         
    	Book testBook = Book.builder().name("Test Book").cost(2).id(1).build();     
        List<Book> allBooks = Arrays.asList(testBook);     
        given(bookService.findAllBooks()).willReturn(allBooks);
     
        mvc.perform(get("/books")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].name", is(testBook.getName())));
    }
    
}
