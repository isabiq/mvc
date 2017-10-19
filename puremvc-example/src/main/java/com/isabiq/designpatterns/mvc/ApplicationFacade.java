package com.isabiq.designpatterns.mvc;

import org.puremvc.java.multicore.patterns.facade.Facade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.controllers.AddAuthorCommand;
import com.isabiq.designpatterns.mvc.controllers.AddBookCommand;
import com.isabiq.designpatterns.mvc.controllers.StartupCommand;

/**
 * Register the commands and start the application through a startup notification.
 * 
 * @author Sabiq Ihab
 *
 */
public class ApplicationFacade extends Facade {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationFacade.class);

  public static final String STARTUP = "startup";

  public static final String ADD_AUTHOR = "addAuthor";

  public static final String AUTHOR_ADDED = "authorAdded";

  public static final String ADD_BOOK = "addBook";

  public static final String BOOK_ADDED = "bookAdded";

  public static final String AUTHOR_ON = "authonOn";

  public static final String BOOK_ON = "bookOn";

  public static final String ADD_AUTHOR_ERROR = "addAuthorError";

  public static final String ADD_BOOK_ERROR = "addBookError";

  protected ApplicationFacade(String key) {
    super(key);
  }

  public static ApplicationFacade getInstance(String key) {
    if (instanceMap.get(key) == null) {
      try {
        new ApplicationFacade(key);
      } catch (RuntimeException e) {
        LOGGER.info("Error Initializing ApplicationFacade", e);
      }
    }
    return (ApplicationFacade) instanceMap.get(key);
  }

  /**
   * Start the application.
   */
  public final void startup() {
    sendNotification(STARTUP);
  }

  /**
   * Initialize controller. Register the commands.
   */
  @Override
  protected final void initializeController() {
    LOGGER.info("Registering the commands for notifications.");
    super.initializeController();
    registerCommand(STARTUP, new StartupCommand());
    registerCommand(ADD_AUTHOR, new AddAuthorCommand());
    registerCommand(ADD_BOOK, new AddBookCommand());
  }

}
