package com.infnet.books.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.books.controller.BookController;
import com.infnet.books.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.infnet.books.utils.BookGenerator.book;
import static com.infnet.books.utils.BookGenerator.someBooks;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("when I want an list of books then success.")
  public void shouldReturnAnListOfBooks() throws Exception {

    given(bookService.getAllBooks()).willReturn(someBooks());

    this.mockMvc
            .perform(get("/api/books")
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.size()", is(3)))
            .andExpect(jsonPath("$[0].isbn", is("IASIASDJQ-120301")))
            .andExpect(jsonPath("$[0].title", is("Utilizando UML e Padrões")));
  }


  @Test
  @DisplayName("given that i want to insert an book, then success")
  public void shouldInsertANewBook() throws Exception {
    given(bookService.createBook(book())).willReturn(book());

    this.mockMvc.perform(
            post("/api/books")
                    .content(objectMapper.writeValueAsString(book()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
  }

  @Test
  @DisplayName("given that I want to get an book then i got success")
  public void shouldGetAnBookById() throws Exception {

    given(bookService.getById(2L)).willReturn(book());

    this.mockMvc
            .perform(get("/api/books/2")
                    .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
            .andExpect(status().is(200))
            .andExpect(jsonPath("$.size()", is(7)));
  }
}
