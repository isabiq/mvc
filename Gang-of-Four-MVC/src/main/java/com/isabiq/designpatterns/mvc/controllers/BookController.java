package com.isabiq.designpatterns.mvc.controllers;

import java.awt.event.ActionEvent;

import com.isabiq.designpatterns.mvc.factory.IController;
import com.isabiq.designpatterns.mvc.model.Book;
import com.isabiq.designpatterns.mvc.service.IService;
import com.isabiq.designpatterns.mvc.service.ServiceImpl;
import com.isabiq.designpatterns.mvc.views.BookView;

public class BookController implements IController {

  private AuthorController authorController;
  private final BookView bookView;
  private IService service;

  public BookController(BookView bookView) {
    this.bookView = bookView;
    service = new ServiceImpl();
    register();
  }

  private void register() {
    bookView.getAddButton().addActionListener(this::addButtonHandler);
    bookView.getSwitchButton().addActionListener(this::switchButtonHandler);
  }

  private void switchButtonHandler(ActionEvent actionEvent) {
    closeView();
    authorController.openView();
  }

  public void openView() {
    bookView.setVisible(true);
    bookView.repaint();
  }

  @Override
  public void closeView() {
    bookView.setVisible(false);
    bookView.repaint();
  }

  private void addButtonHandler(ActionEvent actionEvent) {
    // get user inputs
    String title = bookView.getInput_1().getText();
    String price = bookView.getInput_2().getText();
    String name = bookView.getBookAuthorNameInput().getText();

    try {
      // check and validate inputs
      if (title.isEmpty() || price.isEmpty() || name.isEmpty()) {
        throw new RuntimeException("Please Fill in all the fields");
      }
      service.addBook(new Book(title, Float.parseFloat(price)), name);
      bookView.getBookTable().setItems(service.getBooks());
      bookView.getBookTable().fireTableDataChanged();
    } catch (Exception e) {
      // LOGGER.error("An error occurred ", e);
      bookView.displayMessage("Invalid Input ! " + e.getMessage());
    }
    clean();

  }

  public void setAuthorController(AuthorController authorController) {
    this.authorController = authorController;
  }

  private void clean() {
    bookView.getInput_1().setText("");
    bookView.getInput_2().setText("");
    bookView.getBookAuthorNameInput().setText("");
  }

}
