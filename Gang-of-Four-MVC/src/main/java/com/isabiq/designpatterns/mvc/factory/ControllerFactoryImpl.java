package com.isabiq.designpatterns.mvc.factory;

import com.isabiq.designpatterns.mvc.controllers.AuthorController;
import com.isabiq.designpatterns.mvc.controllers.BookController;
import com.isabiq.designpatterns.mvc.controllers.TopController;
import com.isabiq.designpatterns.mvc.model.Model;
import com.isabiq.designpatterns.mvc.views.AuthorView;
import com.isabiq.designpatterns.mvc.views.BookView;
import com.isabiq.designpatterns.mvc.views.TopView;

public class ControllerFactoryImpl extends ControllerFactory {

  @Override
  public IController getDefaultController() {

    Model subject = new Model();

    BookView bookView = new BookView();
    BookController bookController = new BookController(bookView, subject);
    bookController.closeView();

    AuthorView authorView = new AuthorView();
    AuthorController authorController = new AuthorController(authorView, subject);
    authorController.openView();

    authorController.setBookController(bookController);
    bookController.setAuthorController(authorController);

    TopView mainView = new TopView();
    mainView.add(bookView);
    mainView.add(authorView);

    return new TopController(mainView);
  }

}
