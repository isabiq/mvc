package com.isabiq.designpatterns.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point of the application. Create and initialize the ApplicationFacade and starts the application.
 * 
 * @author Sabiq Ihab
 *
 */
public class App {
  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    ApplicationFacade applicationFacade = new ApplicationFacade("singleFacade");
    LOGGER.info("Start GUI");
    applicationFacade.startup();
  }
}
