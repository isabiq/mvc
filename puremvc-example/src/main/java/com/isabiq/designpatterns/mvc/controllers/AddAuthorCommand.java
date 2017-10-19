package com.isabiq.designpatterns.mvc.controllers;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.ApplicationFacade;
import com.isabiq.designpatterns.mvc.model.AuthorProxy;
import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;

/**
 * Class responsible for adding a new Author through the AuthorProxy and send notification back to the Mediator.
 * 
 * @author Sabiq Ihab
 *
 */
public class AddAuthorCommand extends SimpleCommand {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddAuthorCommand.class);

  @Override
  public void execute(INotification notification) {
    LOGGER.info("Execute add author command.");
    AuthorVO authorVO = (AuthorVO) notification.getBody();
    AuthorProxy userProxy = (AuthorProxy) getFacade().retrieveProxy(AuthorProxy.NAME);
    try {
      userProxy.addAuthor(authorVO);
      userProxy.updateAuthors();
      sendNotification(ApplicationFacade.AUTHOR_ADDED);
    } catch (Exception e) {
      sendNotification(ApplicationFacade.ADD_AUTHOR_ERROR, e);
    }

  }

}
