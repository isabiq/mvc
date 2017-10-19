package com.isabiq.designpatterns.mvc.views.components;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isabiq.designpatterns.mvc.model.vo.AuthorVO;

/**
 * This class represents one of the view components. It's responsible for displaying author information.
 * 
 * @author Sabiq Ihab
 *
 */
public class AuthorTable extends AbstractTableModel {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorTable.class);
  private List<AuthorVO> items;
  private static final String[] HEADERS = { "Name", "Email" };

  public AuthorTable() {
    super();
  }

  public void setItems(List<AuthorVO> items) {
    this.items = items;
    LOGGER.info("New list authors ");
  }

  @Override
  public int getRowCount() {
    if (items != null) {
      return items.size();
    }
    return 0;
  }

  @Override
  public int getColumnCount() {
    return HEADERS.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return HEADERS[columnIndex];
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex) {
    case 0:
      return items.get(rowIndex).getName();
    case 1:
      return items.get(rowIndex).getEmail();
    default:
      return null;
    }

  }
}
