package com.isabiq.designpatterns.mvc.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.model.Author;
import com.isabiq.designpatterns.mvc.model.Book;
import com.isabiq.designpatterns.mvc.util.HibernateUtil;
import com.isabiq.designpatterns.mvc.views.View;

/**
 * The controller class is responsible for handling user actions, making decisions accordingly and sending to its views.
 * 
 * @author Sabiq Ihab
 *
 */
public class Controller {
  private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
  private View view = new View();

  public Controller() {
    register();
  }

  public void start() {
    view.setVisible(true);
  }

  private void register() {
    view.getSwitchButton().addActionListener(this::switchViewstate);
    view.getAddButton().addActionListener(this::addButtonHandler);

    view.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        HibernateUtil.getSessionFactory().close();
      }
    });
  }

  private void switchViewstate(ActionEvent actionEvent) {
    switch (view.getViewState()) {
    case View.AUTHOR_STATE:
      view.setViewState(View.BOOK_STATE);
      break;
    case View.BOOK_STATE:
      view.setViewState(View.AUTHOR_STATE);
      break;
    }
    clean();
    view.repaint();
  }

  private void addButtonHandler(ActionEvent actionEvent) {
    switch (view.getViewState()) {
    case View.AUTHOR_STATE:
      addAuthorAction();
      break;
    case View.BOOK_STATE:
      addBookAction();
      break;
    }
    clean();

  }

  private void addAuthorAction() {
    // get user inputs
    String name = view.getInput_1().getText();
    String email = view.getInput_2().getText();

    try {
      // check and validate inputs
      if (name.isEmpty() || email.isEmpty()) {
        throw new RuntimeException("Please Fill in all the fields");
      }

      view.addAuthor(new Author(name, email));
    } catch (Exception e) {
      LOGGER.error("An error occured ", e);
      view.displayMessage("Invalid Input ! " + e.getMessage());
    }
  }

  private void addBookAction() {
    // get user inputs
    String title = view.getInput_1().getText();
    String price = view.getInput_2().getText();
    String name = view.getBookAuthorNameInput().getText();

    try {
      // check and validate inputs
      if (title.isEmpty() || price.isEmpty() || name.isEmpty()) {
        throw new RuntimeException("Please Fill in all the fields");
      }

      view.addBook(new Book(title, Float.parseFloat(price)), name);
    } catch (Exception e) {
      LOGGER.error("An error occured ", e);
      view.displayMessage("Invalid Input !  " + e.getMessage());
    }

  }

  private void clean() {
    view.getInput_1().setText("");
    view.getInput_2().setText("");
    view.getBookAuthorNameInput().setText("");
  }

}
