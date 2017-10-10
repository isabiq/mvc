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
import com.isabiq.designpatterns.mvc.views.components.BookTable;

/**
 * Book View.
 * 
 * @author Sabiq Ihab
 *
 */
public class BookView extends JPanel implements Observer {

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

  private JLabel bookTitle;
  private JLabel price;
  private BookTable bookTable;
  private JLabel bookAuthorNameLabel;
  private JTextField bookAuthorNameInput;

  public BookView() {
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

    bookTitle = new JLabel("Title");
    bookTitle.setBounds(30, 30, 50, 20);
    this.add(bookTitle);

    price = new JLabel("Price");
    price.setBounds(30, 60, 50, 20);
    this.add(price);

    bookTable = new BookTable();

    table = new JTable(bookTable);
    tableJScrollPane = new JScrollPane(table);
    tableJScrollPane.setBounds(10, 180, 380, 240);
    this.add(tableJScrollPane);

    bookAuthorNameInput = new JTextField();
    bookAuthorNameInput.setBounds(90, 90, 160, 25);
    this.add(bookAuthorNameInput);

    bookAuthorNameLabel = new JLabel("Author");
    bookAuthorNameLabel.setBounds(30, 90, 50, 20);
    this.add(bookAuthorNameLabel);
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

  public JLabel getBookTitle() {
    return bookTitle;
  }

  public JLabel getPrice() {
    return price;
  }

  public BookTable getBookTable() {
    return bookTable;
  }

  public JLabel getBookAuthorNameLabel() {
    return bookAuthorNameLabel;
  }

  public JTextField getBookAuthorNameInput() {
    return bookAuthorNameInput;
  }

  @Override
  public void update(Observable o, Object arg) {
    bookTable.setItems(service.getBooks());
    bookTable.fireTableDataChanged();
  }

}
