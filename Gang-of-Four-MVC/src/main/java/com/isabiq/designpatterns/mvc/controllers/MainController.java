package com.isabiq.designpatterns.mvc.controllers;

import javax.swing.JFrame;

import com.isabiq.designpatterns.mvc.factory.IController;
import com.isabiq.designpatterns.mvc.views.MainView;

public class MainController implements IController {
  private final MainView mainView;

  public MainController(MainView mainView) {
    this.mainView = mainView;
  }

  @Override
  public void openView() {
    JFrame frame = new JFrame("Library");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    frame.setBounds(200, 100, 400, 500);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.add(mainView);
    mainView.setVisible(true);
    frame.repaint();
  }

  @Override
  public void closeView() {
    // TODO Auto-generated method stub
    
  }

}
