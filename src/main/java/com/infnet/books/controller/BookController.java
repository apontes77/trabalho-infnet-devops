package com.infnet.books.controller;

import com.infnet.books.domain.Book;
import com.infnet.books.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/books", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> allBooks() {
        log.debug("getting all the books from the database");
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book insert(@RequestBody Book book) {
        log.info("inserting book");
        return bookService.createBook(book);
    }

    @GetMapping(value = "/{id}")
    public Book getbyId(@PathVariable final Long id) {
        log.trace("getting an specific book");
        return bookService.getById(id);
    }

    @GetMapping("/top10")
    public List<Book> top10() {
        log.trace("getting the first ten books");
        return bookService.top10();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBookById(@PathVariable final Long id) {
        log.trace("deleting the book with id={}", id);
        bookService.deleteBook(id);
    }
}
