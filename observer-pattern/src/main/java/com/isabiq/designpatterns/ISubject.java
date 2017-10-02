package com.isabiq.designpatterns;

public interface ISubject {

  public void attach(IObserver observer);

  public void dettach(IObserver observer);

  public void sendNotification();
}
