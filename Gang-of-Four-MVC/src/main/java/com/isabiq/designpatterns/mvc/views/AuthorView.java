package com.isabiq.designpatterns.mvc.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AuthorView {

  private JPanel mainPanel;
  private JTextField input_1;
  private JTextField input_2;
  private JButton addButton;
  private JScrollPane tableJScrollPane;
  private JTable table;

  private JLabel authorName;
  private JLabel email;

  public AuthorView() {
    init();
  }

  private void init() {

  }

  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE);
  }

  void add(MainView mainView) {
    mainView.add(mainPanel);
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }

  public JTextField getInput_1() {
    return input_1;
  }

  public JTextField getInput_2() {
    return input_2;
  }

  public JButton getAddButton() {
    return addButton;
  }

  public JScrollPane getTableJScrollPane() {
    return tableJScrollPane;
  }

  public JTable getTable() {
    return table;
  }

  public JLabel getAuthorName() {
    return authorName;
  }

  public JLabel getEmail() {
    return email;
  }

}
