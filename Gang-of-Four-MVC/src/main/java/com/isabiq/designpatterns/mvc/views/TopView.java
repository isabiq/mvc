package com.isabiq.designpatterns.mvc.views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Top View used as a container for all the other views.
 * 
 * @author Sabiq Ihab
 *
 */
public class TopView extends JPanel implements Observer {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public TopView() {
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
