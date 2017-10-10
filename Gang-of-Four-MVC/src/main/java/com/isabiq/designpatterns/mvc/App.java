package com.isabiq.designpatterns.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.factory.ControllerFactory;
import com.isabiq.designpatterns.mvc.factory.ControllerFactoryImpl;
import com.isabiq.designpatterns.mvc.factory.IController;

/**
 * Class with main to create and get the default controller from the factory and start the application
 * 
 * @author Sabiq Ihab
 *
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    ControllerFactory factory = new ControllerFactoryImpl();
    IController controller = factory.getDefaultController();
    LOGGER.info("start GUI");
    controller.openView();

  }

}
