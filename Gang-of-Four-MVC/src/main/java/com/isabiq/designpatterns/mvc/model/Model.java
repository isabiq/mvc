package com.isabiq.designpatterns.mvc.model;

import java.util.Observable;

public class Model extends Observable {

  @Override
  public void setChanged() {
    super.setChanged();
  }

}
