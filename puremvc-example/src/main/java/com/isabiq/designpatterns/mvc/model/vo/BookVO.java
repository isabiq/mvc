package com.isabiq.designpatterns.mvc.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Book entity is written by one author.
 * 
 * @author Sabiq Ihab
 *
 */
@Entity
@Table(name = "Book")
public class BookVO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String title;
  private float price;
  @ManyToOne
  private AuthorVO author;

  public BookVO(String title, float price) {
    super();
    this.title = title;
    this.price = price;
  }

  public BookVO() {
    super();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public AuthorVO getAuthor() {
    return author;
  }

  public void setAuthor(AuthorVO author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Book [title=" + title + ", price=" + price + ", author=" + author.getName() + "]";
  }

}
