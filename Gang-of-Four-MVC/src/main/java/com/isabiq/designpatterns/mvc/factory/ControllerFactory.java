package com.isabiq.designpatterns.mvc.factory;

/**
 * Factory interface for creating and getting a controller.
 * 
 * @author Sabiq Ihab
 *
 */
public abstract class ControllerFactory {

  public abstract IController getDefaultController();

}
