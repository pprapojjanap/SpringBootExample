package com.example.SpringBootExample;

import com.example.SpringBootExample.models.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookServiceIntegrationTest {
    private static final String API_ROOT = "http://localhost:9000/api/books";

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> emptyEntity = new HttpEntity<String>(null, headers);

    private Book createBook(long id, String title, String author){
        Book book =  new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }

    @Test
    public void whenAddBook_thenStatusOK() {
        String expected = "{id:1,title:TestTitle,author:TestAuthor}";
        Book book = createBook(1, "TestTitle", "TestAuthor");
        HttpEntity<Book> entity = new HttpEntity<Book>(book, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                API_ROOT + "/add",
                HttpMethod.POST, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        }
        catch (Exception ex)
        {
            new RuntimeException("Assert json failed",ex);
        }
    }

    @Test
    public void whengetBookId1_thenStatusOk() {
        String expected = "{id:1,title:TestTitle,author:TestAuthor}";
        ResponseEntity<String> response = restTemplate.exchange(
                API_ROOT + "/1",
                HttpMethod.GET, emptyEntity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        }
        catch (Exception ex)
        {
            new RuntimeException("Assert json failed",ex);
        }
    }

    @Test
    public void whenUpdateBookId1Title_thenStatusOK() {
        String expected = "{id:1,title:NewTestTitle,author:TestAuthor}";
        Book book = createBook(1, "NewTestTitle", "TestAuthor");
        HttpEntity<Book> entity = new HttpEntity<Book>(book, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                API_ROOT + "/1",
                HttpMethod.PUT, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        try {
            JSONAssert.assertEquals(expected, response.getBody(), false);
        }
        catch (Exception ex)
        {
            new RuntimeException("Assert json failed",ex);
        }
    }

    @Test
    public void whenGetInvalidBookingId_thenStatusBadReq() {
        ResponseEntity<String> response = restTemplate.exchange(
                API_ROOT + "/999",
                HttpMethod.PUT, emptyEntity, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void whenDeleteBookId1_thenStatusOK() {
        ResponseEntity<String> response = restTemplate.exchange(
                API_ROOT + "/1",
                HttpMethod.DELETE, emptyEntity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenDeleteInvalidId_thenStatusServerError() {
        ResponseEntity<String> response = restTemplate.exchange(
                API_ROOT + "/999",
                HttpMethod.DELETE, emptyEntity, String.class);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
