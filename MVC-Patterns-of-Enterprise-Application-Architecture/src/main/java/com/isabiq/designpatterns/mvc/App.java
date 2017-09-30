package com.isabiq.designpatterns.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.views.View;

/**
 * Class with main method which start the application from a controller instance.
 * 
 * @author Sabiq Ihab
 *
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    LOGGER.info("Start GUI");
    new View().start();
  }
}
