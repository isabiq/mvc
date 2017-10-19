package com.isabiq.designpatterns.mvc.model.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author entity.
 * 
 * @author Sabiq Ihab
 *
 */
@Entity
@Table(name = "Author")
public class AuthorVO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String name;
  @Column(unique = true)
  private String email;

  public AuthorVO(String name, String email) {
    super();
    this.name = name;
    this.email = email;
  }

  public AuthorVO() {
    super();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "Author [name=" + name + ", email=" + email + "]";
  }

}
