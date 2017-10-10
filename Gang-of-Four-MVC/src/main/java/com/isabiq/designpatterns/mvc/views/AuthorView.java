package com.isabiq.designpatterns.mvc.views;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.isabiq.designpatterns.mvc.service.IService;
import com.isabiq.designpatterns.mvc.service.ServiceImpl;
import com.isabiq.designpatterns.mvc.views.components.AuthorTable;
/**
 * Author View.
 * 
 * @author Sabiq Ihab
 *
 */
public class AuthorView extends JPanel implements Observer {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private IService service;
  private JButton switchButton;
  private JTextField input_1;
  private JTextField input_2;
  private JButton addButton;
  private JScrollPane tableJScrollPane;
  private JTable table;
  private AuthorTable authorTable;

  private JLabel authorName;
  private JLabel email;

  public AuthorView() {
    service = new ServiceImpl();
    init();
  }

  private void init() {
    this.setLayout(null);
    this.setBounds(0, 20, 400, 600);
    this.setBackground(Color.lightGray);

    switchButton = new JButton("switch");
    switchButton.setBounds(300, 0, 100, 20);
    this.add(switchButton);

    input_1 = new JTextField();
    input_1.setBounds(90, 30, 160, 25);
    this.add(input_1);

    input_2 = new JTextField();
    input_2.setBounds(90, 60, 160, 25);
    this.add(input_2);

    addButton = new JButton("Add");
    addButton.setBounds(180, 130, 90, 30);
    this.add(addButton);

    authorTable = new AuthorTable();

    table = new JTable(authorTable);
    tableJScrollPane = new JScrollPane(table);
    tableJScrollPane.setBounds(10, 180, 380, 240);
    this.add(tableJScrollPane);

    authorName = new JLabel("Name");
    authorName.setBounds(30, 30, 50, 20);
    this.add(authorName);

    email = new JLabel("Email");
    email.setBounds(30, 60, 50, 20);
    this.add(email);

  }

  public AuthorTable getAuthorTable() {
    return authorTable;
  }

  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE);
  }

  public JButton getSwitchButton() {
    return switchButton;
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

  @Override
  public void update(Observable o, Object arg) {
    authorTable.setItems(service.getAuthors());
    authorTable.fireTableDataChanged();
  }

}
