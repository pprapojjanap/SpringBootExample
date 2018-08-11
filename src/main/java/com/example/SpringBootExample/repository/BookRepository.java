package com.example.SpringBootExample.repository;

import com.example.SpringBootExample.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
