package com.isabiq.designpatterns.mvc.controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.isabiq.designpatterns.mvc.factory.IController;
import com.isabiq.designpatterns.mvc.util.HibernateUtil;
import com.isabiq.designpatterns.mvc.views.TopView;

public class TopController implements IController {
  private final TopView topView;

  public TopController(TopView topView) {
    this.topView = topView;
  }

  @Override
  public void openView() {
    JFrame frame = new JFrame("Library");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        HibernateUtil.getSessionFactory().close();
      }
    });
    frame.setLayout(null);
    frame.setBounds(200, 100, 400, 500);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.add(topView);
    topView.setVisible(true);
    frame.repaint();
  }

  @Override
  public void closeView() {
    // TODO Auto-generated method stub

  }

}
