package com.isabiq.designpatterns.mvc.controllers;

import java.awt.event.ActionEvent;

import com.isabiq.designpatterns.mvc.factory.IController;
import com.isabiq.designpatterns.mvc.model.Author;
import com.isabiq.designpatterns.mvc.service.IService;
import com.isabiq.designpatterns.mvc.service.ServiceImpl;
import com.isabiq.designpatterns.mvc.views.AuthorView;

public class AuthorController implements IController {

  private BookController bookController;
  private final AuthorView authorView;
  private IService service;

  public AuthorController(AuthorView authorView) {
    this.authorView = authorView;
    service = new ServiceImpl();
    register();
  }

  private void register() {
    authorView.getAddButton().addActionListener(this::addButtonHandler);
    authorView.getSwitchButton().addActionListener(this::switchButtonHandler);
  }

  private void switchButtonHandler(ActionEvent actionEvent) {
    authorView.setVisible(false);
    authorView.repaint();
    bookController.start();
  }

  public void start() {
    authorView.setVisible(true);
    authorView.repaint();
  }

  private void addButtonHandler(ActionEvent actionEvent) {
    String name = authorView.getInput_1().getText();
    String email = authorView.getInput_2().getText();

    try {
      // check and validate inputs
      if (name.isEmpty() || email.isEmpty()) {
        throw new RuntimeException("Please Fill in all the fields");
      }

      service.addAuthor(new Author(name, email));
      authorView.getAuthorTable().setItems(service.getAuthors());
      authorView.getAuthorTable().fireTableDataChanged();
    } catch (Exception e) {
      // LOGGER.error("An error occurred ", e);
      authorView.displayMessage("Invalid Input ! " + e.getMessage());
    }
    clean();

  }

  public void setBookController(BookController bookController) {
    this.bookController = bookController;
  }

  private void clean() {
    authorView.getInput_1().setText("");
    authorView.getInput_2().setText("");
  }
}
