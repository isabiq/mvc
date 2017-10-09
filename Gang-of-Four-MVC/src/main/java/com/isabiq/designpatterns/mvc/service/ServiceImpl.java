package com.isabiq.designpatterns.mvc.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.isabiq.designpatterns.mvc.model.Author;
import com.isabiq.designpatterns.mvc.model.Book;
import com.isabiq.designpatterns.mvc.util.HibernateUtil;

public class ServiceImpl implements IService {

  private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

  private Author getAuthorByName(String name) {
    Author author = null;
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createQuery("from Author where name=:name");
      query.setParameter("name", name);
      author = (Author) query.uniqueResult();
    }
    if (author == null) {
      throw new NullPointerException("Author " + name + " doesn't exist!");
    }
    return author;
  }

  public void addAuthor(Author author) {
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.save(author);
      session.getTransaction().commit();
    }
  }

  public void deleteAuthor(Author author) {
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.delete(author);
      session.getTransaction().commit();
    }
  }

  @SuppressWarnings("unchecked")
  public List<Author> getAuthors() {
    List<Author> authors = null;
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createQuery("from Author");
      authors = query.list();
    }
    return authors;
  }

  public void addBook(Book book, String authorName) {
    Author author = getAuthorByName(authorName);
    book.setAuthor(author);
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.save(book);
      session.getTransaction().commit();
    }
  }

  public void deleteBook(Book book, String authorName) {
    Author author = getAuthorByName(authorName);
    book.setAuthor(author);
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      session.delete(book);
      session.getTransaction().commit();
    }
  }

  @SuppressWarnings("unchecked")
  public List<Book> getBooks() {
    List<Book> books = null;
    try (Session session = sessionFactory.openSession()) {
      Query query = session.createQuery("from Book");
      books = query.list();
    }
    return books;
  }

}
