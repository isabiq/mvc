package com.isabiq.designpatterns.mvc.views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.puremvc.java.multicore.patterns.mediator.Mediator;

import com.isabiq.designpatterns.mvc.util.HibernateUtil;

public class TopPanelMediator extends Mediator {

  public static final String NAME = "TopPanelMediator";

  private JPanel topJPanel;

  public TopPanelMediator() {
    super(NAME, null);
  }

  @Override
  public final void onRegister() {
    initView();
    super.onRegister();
  }

  private void initView() {
    topJPanel = new JPanel();
    topJPanel.setLayout(null);
    topJPanel.setBounds(0, 0, 400, 500);

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
    frame.add(topJPanel);
    topJPanel.setVisible(true);
    frame.repaint();
  }

  public void add(JPanel panel) {
    topJPanel.add(panel);

  }
}
