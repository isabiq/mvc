package com.isabiq.designpatterns.mvc.model;

import java.util.List;

import org.puremvc.java.multicore.patterns.proxy.Proxy;

import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;
import com.isabiq.designpatterns.mvc.service.IService;
import com.isabiq.designpatterns.mvc.service.ServiceImpl;

public class AuthorProxy extends Proxy {

  public static final String NAME = "AuthorProxy";
  private List<AuthorVO> authors;
  private IService service;

  public AuthorProxy() {
    super(NAME);
    service = new ServiceImpl();
  }

  public void addAuthor(AuthorVO authorVO) {
    service.addAuthor(authorVO);
  }

  public List<AuthorVO> getAuthors() {
    return authors;
  }

  public void updateAuthors() {
    authors = service.getAuthors();
  }
}
