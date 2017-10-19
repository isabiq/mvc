package com.isabiq.designpatterns.mvc.controllers;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

import com.isabiq.designpatterns.mvc.ApplicationFacade;
import com.isabiq.designpatterns.mvc.model.AuthorProxy;
import com.isabiq.designpatterns.mvc.model.BookProxy;
import com.isabiq.designpatterns.mvc.views.AuthorPanelMediator;
import com.isabiq.designpatterns.mvc.views.BookPanelMediator;
import com.isabiq.designpatterns.mvc.views.TopPanelMediator;

public class StartupCommand extends SimpleCommand {

  @Override
  public void execute(INotification notification) {
    getFacade().registerProxy(new AuthorProxy());
    getFacade().registerProxy(new BookProxy());

    TopPanelMediator topPanel = new TopPanelMediator();
    getFacade().registerMediator(topPanel);

    AuthorPanelMediator authorPanel = new AuthorPanelMediator();
    getFacade().registerMediator(authorPanel);

    BookPanelMediator bookPanel = new BookPanelMediator();
    getFacade().registerMediator(bookPanel);

    topPanel.add(authorPanel.getAuthorJPanel());
    topPanel.add(bookPanel.getBookJPanel());

    sendNotification(ApplicationFacade.AUTHOR_ON);

    getFacade().removeCommand(ApplicationFacade.STARTUP);
  }

}
