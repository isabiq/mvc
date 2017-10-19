package com.isabiq.designpatterns.mvc.service;

import java.util.List;

import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;
import com.isabiq.designpatterns.mvc.model.vo.BookVO;

/**
 * This class is responsible for creating, removing and getting authors and books from the database.
 * 
 * @author Sabiq Ihab
 *
 */
public interface IService {

  public void addAuthor(AuthorVO authorVO);

  public void deleteAuthor(AuthorVO authorVO);

  public List<AuthorVO> getAuthors();

  public void addBook(BookVO bookVO, String authorName);

  public void deleteBook(BookVO bookVO, String authorName);

  public List<BookVO> getBooks();

}
