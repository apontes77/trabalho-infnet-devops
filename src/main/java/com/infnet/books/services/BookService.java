package com.infnet.books.services;

import com.infnet.books.domain.Book;
import com.infnet.books.exceptions.BookNotFoundException;
import com.infnet.books.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public List<Book> top10() {
        return bookRepository.findAll(PageRequest.of(0,10, Sort.by(Sort.Direction.ASC, "title")))
                .getContent();
    }

    @Transactional
    public void deleteBook(final Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public Book createBook(Book book) {
      return bookRepository.save(book);
    }
}
