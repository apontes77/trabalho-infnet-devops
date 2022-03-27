package com.infnet.books.utils;

import com.infnet.books.domain.Book;

import java.util.List;

public class BookGenerator {

    public static Book book() {
        return Book.builder()
                .id(2L)
                .title("Implementando Domain-Driven Design")
                .author("Vaughn Vernon")
                .publisher("Alta Books")
                .genre("Software Architecture")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sodales neque sodales ut etiam. Venenatis urna cursus eget nunc. Molestie nunc non blandit massa enim nec dui nunc mattis. Facilisi cras fermentum odio eu. Lacus sed viverra tellus in hac habitasse. Sed arcu non odio euismod lacinia at quis risus sed. Rhoncus aenean vel elit scelerisque mauris pellentesque pulvinar. Aliquam nulla facilisi cras fermentum odio eu feugiat pretium. Sed viverra ipsum nunc aliquet bibendum. Id donec ultrices tincidunt arcu non sodales neque.")
                .isbn("IASIASDJQ-120301")
                .pages(124L)
                .build();
    }

    public static List<Book> someBooks() {
        return List.of(
                Book.builder().id(1L).title("Utilizando UML e Padrões").isbn("IASIASDJQ-120301").pages(123L).build(),
                Book.builder().id(2L).title("Implementando Domain-Driven Design").isbn("IASIASDJQ-120301").pages(124L).build(),
                Book.builder().id(3L).title("Domain-Driven Design").isbn("IASIASDJQ-120301").pages(125L).build());
    }
}
