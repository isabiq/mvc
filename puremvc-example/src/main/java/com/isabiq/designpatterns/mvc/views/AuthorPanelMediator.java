package com.isabiq.designpatterns.mvc.views;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

import com.isabiq.designpatterns.mvc.ApplicationFacade;
import com.isabiq.designpatterns.mvc.model.AuthorProxy;
import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;
import com.isabiq.designpatterns.mvc.views.components.AuthorTable;

public class AuthorPanelMediator extends Mediator {

  public static final String NAME = "AuthorPanelMediator";

  private AuthorProxy authorProxy;

  private JPanel authorJPanel;

  public JPanel getAuthorJPanel() {
    return authorJPanel;
  }

  private JButton switchButton;
  private JTextField input_1;
  private JTextField input_2;
  private JButton addButton;
  private JScrollPane tableJScrollPane;
  private JTable table;
  private AuthorTable authorTable;

  private JLabel authorName;
  private JLabel email;

  public AuthorPanelMediator() {
    super(NAME, null);
  }

  @Override
  public final String[] listNotificationInterests() {
    return new String[] { ApplicationFacade.AUTHOR_ADDED, ApplicationFacade.AUTHOR_ON, ApplicationFacade.BOOK_ON,
        ApplicationFacade.ADD_AUTHOR_ERROR };
  }

  @Override
  public final void handleNotification(final INotification notification) {
    switch (notification.getName()) {
    case ApplicationFacade.AUTHOR_ADDED:
      authorTable.setItems(authorProxy.getAuthors());
      authorTable.fireTableDataChanged();
      clean();
      break;
    case ApplicationFacade.AUTHOR_ON:
      authorJPanel.setVisible(true);
      break;
    case ApplicationFacade.BOOK_ON:
      authorJPanel.setVisible(false);
      break;
    case ApplicationFacade.ADD_AUTHOR_ERROR:
      JOptionPane.showMessageDialog(null, ((Exception) notification.getBody()).getMessage(), "Message",
          JOptionPane.ERROR_MESSAGE);
      break;

    }
  }

  @Override
  public final void onRegister() {
    authorProxy = (AuthorProxy) getFacade().retrieveProxy(AuthorProxy.NAME);
    initView();
    addButton.addActionListener(this::addButtonHandler);
    switchButton.addActionListener(this::switchButtonHandler);
    super.onRegister();
  }

  private void switchButtonHandler(ActionEvent actionEvent) {
    sendNotification(ApplicationFacade.BOOK_ON);
  }

  private void addButtonHandler(ActionEvent actionEvent) {
    String name = input_1.getText();
    String email = input_2.getText();

    // check and validate inputs
    if (name.isEmpty() || email.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Please Fill in all the fields", "Message", JOptionPane.ERROR_MESSAGE);
      return;
    }
    sendNotification(ApplicationFacade.ADD_AUTHOR, new AuthorVO(name, email));

  }

  private void clean() {
    input_1.setText("");
    input_2.setText("");
  }

  /**
   * Initialize the author panel view.
   */
  private void initView() {

    authorJPanel = new JPanel();
    authorJPanel.setLayout(null);
    authorJPanel.setBounds(0, 20, 400, 600);
    authorJPanel.setBackground(Color.lightGray);

    switchButton = new JButton("switch");
    switchButton.setBounds(300, 0, 100, 20);
    authorJPanel.add(switchButton);

    input_1 = new JTextField();
    input_1.setBounds(90, 30, 160, 25);
    authorJPanel.add(input_1);

    input_2 = new JTextField();
    input_2.setBounds(90, 60, 160, 25);
    authorJPanel.add(input_2);

    addButton = new JButton("Add");
    addButton.setBounds(180, 130, 90, 30);
    authorJPanel.add(addButton);

    authorTable = new AuthorTable();

    table = new JTable(authorTable);
    tableJScrollPane = new JScrollPane(table);
    tableJScrollPane.setBounds(10, 180, 380, 240);
    authorJPanel.add(tableJScrollPane);

    authorName = new JLabel("Name");
    authorName.setBounds(30, 30, 50, 20);
    authorJPanel.add(authorName);

    email = new JLabel("Email");
    email.setBounds(30, 60, 50, 20);
    authorJPanel.add(email);

  }

}
