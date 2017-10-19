package com.isabiq.designpatterns.mvc.controllers;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.isabiq.designpatterns.mvc.ApplicationFacade;
import com.isabiq.designpatterns.mvc.model.BookProxy;
import com.isabiq.designpatterns.mvc.model.vo.BookVO;

public class AddBookCommand extends SimpleCommand {

  @Override
  public void execute(INotification notification) {
    BookVO bookVO = (BookVO) notification.getBody();
    BookProxy bookProxy = (BookProxy) getFacade().retrieveProxy(BookProxy.NAME);
    try {
      bookProxy.addBook(bookVO, bookVO.getAuthor().getName());
      bookProxy.updateBooks();
      sendNotification(ApplicationFacade.BOOK_ADDED);
    } catch (Exception e) {
      sendNotification(ApplicationFacade.ADD_BOOK_ERROR, e);
    }

  }

}
