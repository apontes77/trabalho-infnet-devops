package com.infnet.books.services;

import com.infnet.books.domain.Book;
import com.infnet.books.exceptions.BookNotFoundException;
import com.infnet.books.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getById(final Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Livro não encontrado!"));
    }
}
