package com.isabiq.designpatterns.mvc.model;

import java.util.List;

import org.puremvc.java.multicore.patterns.proxy.Proxy;

import com.isabiq.designpatterns.mvc.model.vo.BookVO;
import com.isabiq.designpatterns.mvc.service.IService;
import com.isabiq.designpatterns.mvc.service.ServiceImpl;

public class BookProxy extends Proxy {

  public static final String NAME = "BookProxy";

  private List<BookVO> books;
  private IService service;

  public BookProxy() {
    super(NAME);
    service = new ServiceImpl();
  }

  public void addBook(BookVO bookVO, String authorName) {
    service.addBook(bookVO, authorName);
  }

  public List<BookVO> getBooks() {
    return books;
  }

  public void updateBooks() {
    books = service.getBooks();
  }
}
