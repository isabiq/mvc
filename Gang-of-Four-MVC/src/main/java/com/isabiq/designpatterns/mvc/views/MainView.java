package com.isabiq.designpatterns.mvc.views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class MainView extends JPanel implements Observer {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public MainView() {
    init();
  }

  private void init() {
    this.setLayout(null);
    this.setBounds(0, 0, 400, 500);
  }

  @Override
  public void update(Observable o, Object arg) {
    this.repaint();
  }

}
