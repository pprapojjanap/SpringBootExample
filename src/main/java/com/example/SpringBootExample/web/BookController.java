package com.example.SpringBootExample.web;

import com.example.SpringBootExample.models.Book;
import com.example.SpringBootExample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Iterable findAll(){
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return bookService.findById(id);
    }

    @PostMapping("/add")
    public Book create(@RequestBody Book book){
        Book newBook = bookService.save(book);
        return newBook;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        bookService.findById(id);
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable long id) {
        if (book.getId() != id) {
            throw new RuntimeException();
        }
        bookService.findById(id);
        return bookService.save(book);
    }
}
