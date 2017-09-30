package com.isabiq.designpatterns.mvc.views;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.model.Author;
import com.isabiq.designpatterns.mvc.model.Book;
import com.isabiq.designpatterns.mvc.service.IService;
import com.isabiq.designpatterns.mvc.service.ServiceImpl;
import com.isabiq.designpatterns.mvc.views.components.AuthorTable;
import com.isabiq.designpatterns.mvc.views.components.BookTable;

/**
 * The view contains all the components and data received from the model displayed to the user. It handles all
 * application logic and talk to its model for updates and new data.
 * 
 * @author Sabiq Ihab
 *
 */
public class View extends JFrame {

  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = LoggerFactory.getLogger(View.class);

  private IService service;

  private int viewState = View.AUTHOR_STATE;
  public static final int AUTHOR_STATE = 0;
  public static final int BOOK_STATE = 1;

  private JPanel headerPanel;
  private JButton switchButton;
  private JLabel stateTitle;

  private JPanel mainPanel;
  private JTextField input_1;
  private JTextField input_2;
  private JButton addButton;
  private JScrollPane tableJScrollPane;
  private JTable table;

  private JLabel authorName;
  private JLabel email;

  private JLabel bookTitle;
  private JLabel price;
  private AuthorTable authorTable;
  private BookTable bookTable;
  private JLabel bookAuthorNameLabel;
  private JTextField bookAuthorNameInput;

  public View() {
    initComponents();
    register();
    service = new ServiceImpl();
  }

  /**
   * construct view layout and components
   */
  private void initComponents() {
    this.setTitle("Library");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// TODO close hibernate connection
    this.setLayout(null);
    this.setBounds(200, 100, 400, 500);

    headerPanel = new JPanel();
    headerPanel.setLayout(null);
    headerPanel.setBounds(0, 0, 400, 20);
    headerPanel.setBackground(Color.LIGHT_GRAY);
    this.add(headerPanel);

    switchButton = new JButton("switch");
    switchButton.setBounds(300, 0, 100, 20);
    headerPanel.add(switchButton);

    stateTitle = new JLabel("Authors List");
    stateTitle.setBounds(10, 0, 150, 30);
    headerPanel.add(stateTitle);

    mainPanel = new JPanel();
    this.add(mainPanel);
    mainPanel.setLayout(null);
    mainPanel.setBounds(0, 20, 400, 600);
    mainPanel.setBackground(Color.lightGray);

    input_1 = new JTextField();
    input_1.setBounds(90, 30, 160, 25);
    mainPanel.add(input_1);

    input_2 = new JTextField();
    input_2.setBounds(90, 60, 160, 25);
    mainPanel.add(input_2);

    addButton = new JButton("Add");
    addButton.setBounds(180, 130, 90, 30);
    mainPanel.add(addButton);

    authorTable = new AuthorTable();
    bookTable = new BookTable();

    table = new JTable(authorTable);
    tableJScrollPane = new JScrollPane(table);
    tableJScrollPane.setBounds(10, 180, 380, 240);
    mainPanel.add(tableJScrollPane);

    authorName = new JLabel("Name");
    authorName.setBounds(30, 30, 50, 20);
    mainPanel.add(authorName);

    email = new JLabel("Email");
    email.setBounds(30, 60, 50, 20);
    mainPanel.add(email);

    bookTitle = new JLabel("Title");
    bookTitle.setBounds(30, 30, 50, 20);

    price = new JLabel("Price");
    price.setBounds(30, 60, 50, 20);

    bookAuthorNameInput = new JTextField();
    bookAuthorNameInput.setBounds(90, 90, 160, 25);

    bookAuthorNameLabel = new JLabel("Author");
    bookAuthorNameLabel.setBounds(30, 90, 50, 20);

  }

  private void register() {
    this.switchButton.addActionListener(this::switchViewstate);
    this.addButton.addActionListener(this::addButtonHandler);

  }

  private void switchViewstate(ActionEvent actionEvent) {
    if (viewState == BOOK_STATE) {
      LOGGER.info("View switched to Author state");
      viewState = AUTHOR_STATE;
      mainPanel.remove(bookTitle);
      mainPanel.remove(price);
      mainPanel.remove(bookAuthorNameInput);
      mainPanel.remove(bookAuthorNameLabel);
      mainPanel.add(authorName);
      mainPanel.add(email);
      stateTitle.setText("Authors List");
      table.setModel(authorTable);

    } else if (viewState == AUTHOR_STATE) {
      LOGGER.info("View switched to Book state");
      viewState = BOOK_STATE;
      mainPanel.remove(authorName);
      mainPanel.remove(email);
      mainPanel.add(bookTitle);
      mainPanel.add(price);
      mainPanel.add(bookAuthorNameInput);
      mainPanel.add(bookAuthorNameLabel);
      stateTitle.setText("Books List");
      table.setModel(bookTable);
    }
    clean();
    this.repaint();
  }

  private void addButtonHandler(ActionEvent actionEvent) {
    switch (viewState) {
    case View.AUTHOR_STATE:
      addAuthorAction();
      break;
    case View.BOOK_STATE:
      addBookAction();
      break;
    }
    clean();

  }

  private void addAuthorAction() {
    // get user inputs
    String name = this.input_1.getText();
    String email = this.input_2.getText();

    try {
      // check and validate inputs
      if (name.isEmpty() || email.isEmpty()) {
        throw new RuntimeException("Please Fill in all the fields");
      }

      this.addAuthor(new Author(name, email));
    } catch (Exception e) {
      LOGGER.error("An error occured ", e);
      this.displayMessage("Invalid Input ! " + e.getMessage());
    }
  }

  private void addBookAction() {
    // get user inputs
    String title = this.input_1.getText();
    String price = this.input_2.getText();
    String name = this.bookAuthorNameInput.getText();

    try {
      // check and validate inputs
      if (title.isEmpty() || price.isEmpty() || name.isEmpty()) {
        throw new RuntimeException("Please Fill in all the fields");
      }

      this.addBook(new Book(title, Float.parseFloat(price)), name);
    } catch (Exception e) {
      LOGGER.error("An error occured ", e);
      this.displayMessage("Invalid Input !  " + e.getMessage());
    }

  }

  private void clean() {
    this.input_1.setText("");
    this.input_2.setText("");
    this.bookAuthorNameInput.setText("");
  }

  public void addAuthor(Author author) throws Exception {
    service.addAuthor(author);
    LOGGER.info("{} added", author);
    authorTable.setItems(service.getAuthors());
    authorTable.fireTableDataChanged();
  }

  public void addBook(Book book, String authorName) throws Exception {
    service.addBook(book, authorName);
    LOGGER.info("{} added", book);
    bookTable.setItems(service.getBooks());
    bookTable.fireTableDataChanged();
  }

  private void displayMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.ERROR_MESSAGE);
  }

  public void start() {
    this.setVisible(true);
  }

}
