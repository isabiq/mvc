package com.isabiq.designpatterns.mvc.controllers;

import java.awt.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.factory.IController;
import com.isabiq.designpatterns.mvc.model.Author;
import com.isabiq.designpatterns.mvc.model.Model;
import com.isabiq.designpatterns.mvc.service.IService;
import com.isabiq.designpatterns.mvc.service.ServiceImpl;
import com.isabiq.designpatterns.mvc.views.AuthorView;

/**
 * Class responsible for handling user actions coming from author view.
 * 
 * @author Sabiq Ihab
 *
 */
public class AuthorController implements IController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);

  private BookController bookController;
  private final AuthorView authorView;
  private IService service;
  private Model model;

  public AuthorController(AuthorView authorView, Model model) {
    this.authorView = authorView;
    this.model = model;
    service = new ServiceImpl();
    register();
  }

  private void register() {
    model.addObserver(authorView);
    authorView.getAddButton().addActionListener(this::addButtonHandler);
    authorView.getSwitchButton().addActionListener(this::switchButtonHandler);
  }

  private void switchButtonHandler(ActionEvent actionEvent) {
    closeView();
    bookController.openView();
  }

  public void openView() {
    authorView.setVisible(true);
    authorView.repaint();
  }

  @Override
  public void closeView() {
    authorView.setVisible(false);
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
      model.setChanged();
      model.notifyObservers();
    } catch (Exception e) {
      LOGGER.error("An error occurred ", e);
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
