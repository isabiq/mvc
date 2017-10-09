package com.isabiq.designpatterns.mvc;

import com.isabiq.designpatterns.mvc.factory.ControllerFactory;
import com.isabiq.designpatterns.mvc.factory.ControllerFactoryImpl;
import com.isabiq.designpatterns.mvc.factory.IController;

public class App {

  public static void main(String[] args) {
    ControllerFactory factory = new ControllerFactoryImpl();
    IController controller = factory.getDefaultController();
    controller.openView();

  }

}
