package com.isabiq.designpatterns.mvc.controllers;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.ApplicationFacade;
import com.isabiq.designpatterns.mvc.model.BookProxy;
import com.isabiq.designpatterns.mvc.model.vo.BookVO;

/**
 * Class responsible for adding a new Book through the BookProxy and send notification back to the Mediator.
 * 
 * @author Sabiq Ihab
 *
 */
public class AddBookCommand extends SimpleCommand {
  private static final Logger LOGGER = LoggerFactory.getLogger(AddBookCommand.class);

  @Override
  public void execute(INotification notification) {
    LOGGER.info("Execute add book command.");
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
