package com.example.SpringBootExample.service;

import com.example.SpringBootExample.models.Book;

public interface BookService {
    Iterable<Book> findByTitle(String title);
    Book findById(long id);
    Iterable<Book> findAll();
    Book save(Book newBook);
    void deleteById(long id);
}
