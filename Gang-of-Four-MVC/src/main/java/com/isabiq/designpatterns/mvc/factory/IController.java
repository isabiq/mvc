package com.isabiq.designpatterns.mvc.factory;

/**
 * Interface that must be implemented by all application controllers.
 * 
 * @author Sabiq Ihab
 *
 */
public interface IController {

  void openView();

  void closeView();
}
