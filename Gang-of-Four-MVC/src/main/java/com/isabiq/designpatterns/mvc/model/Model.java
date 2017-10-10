package com.isabiq.designpatterns.mvc.model;

import java.util.Observable;

/**
 * Class used as a subject used notify the views that the model has changed.
 * 
 * @author Sabiq Ihab
 *
 */
public class Model extends Observable {

  @Override
  public void setChanged() {
    super.setChanged();
  }

}
