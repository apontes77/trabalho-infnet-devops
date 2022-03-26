package com.infnet.books.integration;

import com.infnet.books.controller.BookController;
import com.infnet.books.domain.Book;
import com.infnet.books.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("tests of book controller")
@WebMvcTest(BookController.class)
public class BookControllerIT{

  @MockBean
  private BookService bookService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("when I want an list of books then success.")
  public void shouldReturnAnListOfBooks() throws Exception {

    when(bookService.getAllBooks()).thenReturn(someBooks());

    this.mockMvc
            .perform(get("/api/books")
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.size()", is(3)))
            .andExpect(jsonPath("$[0].isbn", is("IASIASDJQ-120301")))
            .andExpect(jsonPath("$[0].name", is("Utilizando UML e Padrões")));
  }

  private List<Book> someBooks() {
    return List.of(
            Book.builder().id(1L).title("Utilizando UML e Padrões").isbn("IASIASDJQ-120301").pages(123L).build(),
            Book.builder().id(2L).title("Implementando Domain-Driven Design").isbn("IASIASDJQ-120301").pages(124L).build(),
            Book.builder().id(3L).title("Domain-Driven Design").isbn("IASIASDJQ-120301").pages(125L).build());
  }


}
