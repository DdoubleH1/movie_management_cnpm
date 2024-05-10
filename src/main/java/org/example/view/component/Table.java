package org.example.view.component;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Table extends JTable {
    public Table(Object[][] data, Object[] columnNames, int[] columnWidths) {
        super(data, columnNames);
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setMaxWidth(columnWidths[i]);
            column.setMinWidth(columnWidths[i]);
        }
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
