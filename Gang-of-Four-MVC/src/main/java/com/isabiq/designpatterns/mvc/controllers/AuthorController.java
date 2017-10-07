package com.isabiq.designpatterns.mvc.controllers;

import java.awt.event.ActionEvent;

import com.isabiq.designpatterns.mvc.views.AuthorView;

public class AuthorController {

  private AuthorView authorView = new AuthorView();

  public AuthorController() {
    register();
  }

  private void register() {
    authorView.getAddButton().addActionListener(this::addButtonHandler);

  }

  private void addButtonHandler(ActionEvent actionEvent) {
    String name = authorView.getInput_1().getText();
    String email = authorView.getInput_2().getText();

    try {
      // check and validate inputs
      if (name.isEmpty() || email.isEmpty()) {
        throw new RuntimeException("Please Fill in all the fields");
      }

      // authorView.addAuthor(new Author(name, email));
    } catch (Exception e) {
      // LOGGER.error("An error occurred ", e);
      authorView.displayMessage("Invalid Input ! " + e.getMessage());
    }
    clean();

  }

  private void clean() {
    authorView.getInput_1().setText("");
    authorView.getInput_2().setText("");
  }
}
