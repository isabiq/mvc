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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.ApplicationFacade;
import com.isabiq.designpatterns.mvc.model.BookProxy;
import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;
import com.isabiq.designpatterns.mvc.model.vo.BookVO;
import com.isabiq.designpatterns.mvc.views.components.BookTable;

/**
 * Class responsible for creating the book view, handling user actions and listening to notifications for views changes.
 * 
 * @author Sabiq Ihab
 *
 */
public class BookPanelMediator extends Mediator {

  public static final String NAME = "BookPanelMediator";

  private static final Logger LOGGER = LoggerFactory.getLogger(BookPanelMediator.class);

  private JPanel bookJPanel;

  public JPanel getBookJPanel() {
    return bookJPanel;
  }

  private BookProxy bookProxy;

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

  public BookPanelMediator() {
    super(NAME, null);
  }

  @Override
  public final String[] listNotificationInterests() {
    return new String[] { ApplicationFacade.BOOK_ADDED, ApplicationFacade.AUTHOR_ON, ApplicationFacade.BOOK_ON,
        ApplicationFacade.ADD_BOOK_ERROR };
  }

  @Override
  public final void handleNotification(final INotification notification) {
    switch (notification.getName()) {
    case ApplicationFacade.BOOK_ADDED:
      LOGGER.info("New Book added.");
      bookTable.setItems(bookProxy.getBooks());
      bookTable.fireTableDataChanged();
      clean();
      break;
    case ApplicationFacade.AUTHOR_ON:
      bookJPanel.setVisible(false);
      break;
    case ApplicationFacade.BOOK_ON:
      LOGGER.info("Switched to Book View.");
      bookJPanel.setVisible(true);
      break;
    case ApplicationFacade.ADD_BOOK_ERROR:
      LOGGER.error(((Exception) notification.getBody()).getMessage(), ((Exception) notification.getBody()));
      JOptionPane.showMessageDialog(null, ((Exception) notification.getBody()).getMessage(), "Message",
          JOptionPane.ERROR_MESSAGE);
      break;

    }
  }

  @Override
  public final void onRegister() {
    bookProxy = (BookProxy) getFacade().retrieveProxy(BookProxy.NAME);
    super.onRegister();
    initView();
    addButton.addActionListener(this::addButtonHandler);
    switchButton.addActionListener(this::switchButtonHandler);
  }

  private void switchButtonHandler(ActionEvent actionEvent) {
    sendNotification(ApplicationFacade.AUTHOR_ON);
    // closeView();
    // authorController.openView();

  }

  private void addButtonHandler(ActionEvent actionEvent) {
    // get user inputs
    String title = input_1.getText();
    String price = input_2.getText();
    String name = bookAuthorNameInput.getText();
    float numericPrice = 0;

    // check and validate inputs
    if (title.isEmpty() || price.isEmpty() || name.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Please Fill in all the fields", "Message", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try {
      numericPrice = Float.parseFloat(price);
    } catch (NumberFormatException e) {
      LOGGER.error(e.getMessage(), e);
      JOptionPane.showMessageDialog(null, "Wrong Price Input " + e.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
      return;
    }
    BookVO book = new BookVO(title, numericPrice);
    book.setAuthor(new AuthorVO(name, null));
    sendNotification(ApplicationFacade.ADD_BOOK, book);
  }

  private void clean() {
    input_1.setText("");
    input_2.setText("");
    bookAuthorNameInput.setText("");
  }

  /**
   * Initialize the book panel view.
   */
  private void initView() {

    bookJPanel = new JPanel();
    bookJPanel.setLayout(null);
    bookJPanel.setBounds(0, 20, 400, 600);
    bookJPanel.setBackground(Color.lightGray);

    switchButton = new JButton("switch");
    switchButton.setBounds(300, 0, 100, 20);
    bookJPanel.add(switchButton);

    input_1 = new JTextField();
    input_1.setBounds(90, 30, 160, 25);
    bookJPanel.add(input_1);

    input_2 = new JTextField();
    input_2.setBounds(90, 60, 160, 25);
    bookJPanel.add(input_2);

    addButton = new JButton("Add");
    addButton.setBounds(180, 130, 90, 30);
    bookJPanel.add(addButton);

    bookTitle = new JLabel("Title");
    bookTitle.setBounds(30, 30, 50, 20);
    bookJPanel.add(bookTitle);

    price = new JLabel("Price");
    price.setBounds(30, 60, 50, 20);
    bookJPanel.add(price);

    bookTable = new BookTable();

    table = new JTable(bookTable);
    tableJScrollPane = new JScrollPane(table);
    tableJScrollPane.setBounds(10, 180, 380, 240);
    bookJPanel.add(tableJScrollPane);

    bookAuthorNameInput = new JTextField();
    bookAuthorNameInput.setBounds(90, 90, 160, 25);
    bookJPanel.add(bookAuthorNameInput);

    bookAuthorNameLabel = new JLabel("Author");
    bookAuthorNameLabel.setBounds(30, 90, 50, 20);
    bookJPanel.add(bookAuthorNameLabel);
  }

}
