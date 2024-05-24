package org.example.view.component;

import org.example.constant.TableConstant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Table extends JTable {

    public Table(Object[][] data, Object[] columnNames, int[] columnWidths, int selectionMode) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        TableColumnModel columnModel = getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(columnWidths[i]);
        }
        setSelectionMode(selectionMode);
    }

    public void updateTableData(Object[][] data, Object[] columnNames) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        setModel(model);
    }

}
