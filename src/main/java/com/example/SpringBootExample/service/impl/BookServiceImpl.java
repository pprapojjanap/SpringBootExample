package com.example.SpringBootExample.service.impl;

import com.example.SpringBootExample.models.Book;
import com.example.SpringBootExample.repository.BookRepository;
import com.example.SpringBootExample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> findByTitle(String title){
      return  bookRepository.findAll();
    }
    @Override
    public Book findById(long id){
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    @Override
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }
    @Override
    public Book save(Book newBook){
        return bookRepository.save(newBook);
    }
    @Override
    public void deleteById(long id){
        bookRepository.deleteById(id);
    }
}
