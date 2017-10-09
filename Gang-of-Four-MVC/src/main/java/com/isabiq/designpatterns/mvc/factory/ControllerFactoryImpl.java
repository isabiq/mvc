package com.isabiq.designpatterns.mvc.factory;

import com.isabiq.designpatterns.mvc.controllers.AuthorController;
import com.isabiq.designpatterns.mvc.controllers.BookController;
import com.isabiq.designpatterns.mvc.controllers.MainController;
import com.isabiq.designpatterns.mvc.views.AuthorView;
import com.isabiq.designpatterns.mvc.views.BookView;
import com.isabiq.designpatterns.mvc.views.MainView;

public class ControllerFactoryImpl extends ControllerFactory {

  @Override
  public IController getDefaultController() {

    BookView bookView = new BookView();
    BookController bookController = new BookController(bookView);

    AuthorView authorView = new AuthorView();
    AuthorController authorController = new AuthorController(authorView);

    authorController.setBookController(bookController);
    bookController.setAuthorController(authorController);

    MainView mainView = new MainView();
    mainView.add(bookView);
    mainView.add(authorView);

    return new MainController(mainView);
  }

}
