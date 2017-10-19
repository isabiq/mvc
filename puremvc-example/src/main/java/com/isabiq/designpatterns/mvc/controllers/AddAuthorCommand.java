package com.isabiq.designpatterns.mvc.controllers;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.isabiq.designpatterns.mvc.ApplicationFacade;
import com.isabiq.designpatterns.mvc.model.AuthorProxy;
import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;

public class AddAuthorCommand extends SimpleCommand {

  @Override
  public void execute(INotification notification) {
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
