package com.isabiq.designpatterns.mvc.controllers;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.factory.IController;
import com.isabiq.designpatterns.mvc.util.HibernateUtil;
import com.isabiq.designpatterns.mvc.views.TopView;

/**
 * Class responsible for creating and configuring the frame.
 * 
 * @author Sabiq Ihab
 *
 */
public class TopController implements IController {
  private static final Logger LOGGER = LoggerFactory.getLogger(TopController.class);

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
    LOGGER.error("Can't call close on this view", new RuntimeException());

  }

}
