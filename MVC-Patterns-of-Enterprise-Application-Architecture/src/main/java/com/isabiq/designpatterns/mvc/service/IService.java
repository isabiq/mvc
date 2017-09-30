package com.isabiq.designpatterns.mvc.service;

import java.util.List;

import com.isabiq.designpatterns.mvc.model.Author;
import com.isabiq.designpatterns.mvc.model.Book;

/**
 * This class is responsible for creating, removing and getting authors and books from the database.
 * 
 * @author Sabiq Ihab
 *
 */
public interface IService {

  public void addAuthor(Author author);

  public void deleteAuthor(Author author);

  public List<Author> getAuthors();

  public void addBook(Book book, String authorName);

  public void deleteBook(Book book, String authorName);

  public List<Book> getBooks();

}
