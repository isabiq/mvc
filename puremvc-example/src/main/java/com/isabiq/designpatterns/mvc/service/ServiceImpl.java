package com.isabiq.designpatterns.mvc.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;
import com.isabiq.designpatterns.mvc.model.vo.BookVO;
import com.isabiq.designpatterns.mvc.util.HibernateUtil;

public class ServiceImpl implements IService {

  private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  private AuthorVO getAuthorByName(String name) {
    AuthorVO author = null;
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createQuery("from AuthorVO where name=:name");
      query.setParameter("name", name);
      author = (AuthorVO) query.uniqueResult();
    }
    if (author == null) {
      throw new NullPointerException("Author " + name + " doesn't exist!");
    }
    return author;
  }

  public void addAuthor(AuthorVO author) {
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.save(author);
      session.getTransaction().commit();
    }
  }

  public void deleteAuthor(AuthorVO author) {
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.delete(author);
      session.getTransaction().commit();
    }
  }

  @SuppressWarnings("unchecked")
  public List<AuthorVO> getAuthors() {
    List<AuthorVO> authors = null;
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createQuery("from AuthorVO");
      authors = query.list();
    }
    return authors;
  }

  public void addBook(BookVO book, String authorName) {
    AuthorVO author = getAuthorByName(authorName);
    book.setAuthor(author);
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.save(book);
      session.getTransaction().commit();
    }
  }

  public void deleteBook(BookVO book, String authorName) {
    AuthorVO author = getAuthorByName(authorName);
    book.setAuthor(author);
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.delete(book);
      session.getTransaction().commit();
    }
  }

  @SuppressWarnings("unchecked")
  public List<BookVO> getBooks() {
    List<BookVO> books = null;
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createQuery("from BookVO");
      books = query.list();
    }
    return books;
  }

}
