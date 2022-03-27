package com.infnet.books.unit;

import com.infnet.books.domain.Book;
import com.infnet.books.repositories.BookRepository;
import com.infnet.books.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.infnet.books.utils.BookGenerator.book;
import static com.infnet.books.utils.BookGenerator.someBooks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("tests of book service")
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService cut;

    @Mock
    private BookRepository bookRepository;

    @DisplayName("dependencies should not be null")
    @Test
    public void dependenciesShouldNotBeNull() {
        assertNotNull(cut);
        assertNotNull(bookRepository);
    }

    @Test
    @DisplayName("given that i want to insert an new book, then success")
    public void shouldInsertNewBook() {

        given(bookRepository.save(book())).willAnswer(invocation -> invocation.getArgument(0));

        Book savedBook = cut.createBook(book());
        assertThat(savedBook).isNotNull();
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void shouldReturnAnListOfBooks() {
        given(cut.getAllBooks()).willReturn(someBooks());

        List<Book> allBooks = cut.getAllBooks();

        assertThat(allBooks).isNotEmpty();
        assertThat(allBooks).isNotNull();
    }

    @Test
    @DisplayName("should throw an exception if an book doesn't exists.")
    public void shouldThrowBookNotFoundExceptionWhenBookIdIsNotExisting() {
        given(bookRepository.findById(2L)).willReturn(null);
        assertThrows(NullPointerException.class, () -> cut.getById(2L));
    }

    @Test
    @DisplayName("given that an valid id is passed, then success")
    public void shouldReturnOneBookById() {
        final Long id = 2L;
        final Book book = book();

        given(bookRepository.findById(id)).willReturn(Optional.of(book));

        final Book expected = cut.getById(id);

        assertThat(expected).isNotNull();
    }

    @Test
    @DisplayName("given that i want to delete an book, then success")
    public void shouldBeDelete() {
        final Long id = 2l;
        cut.deleteBook(2l);
        verify(bookRepository, times(1)).deleteById(id);
    }

}
