package com.isabiq.designpatterns.mvc.views;

import javax.swing.JPanel;

public class MainView extends JPanel {

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

}
