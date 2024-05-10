package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.FoodItemDAO;
import org.example.mapper.TableMapper;
import org.example.model.FoodItem;
import org.example.view.component.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SellFoodFrm extends JFrame implements ActionListener {
    private JPanel sellFoodView;
    private JTextField foodNameTextField;
    private JButton searchButton;
    private JTable foodResultTable;
    private JButton processPaymentButton;
    private JTextField sizeTextField;
    private JTextField quantityTextField;
    private JButton addToInvoiceButton;
    private final FoodItemDAO foodItemDAO = new FoodItemDAO();

    public SellFoodFrm() {
        setTitle("Sell Food");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(sellFoodView);
        searchButton.addActionListener(this);
        processPaymentButton.addActionListener(this);
        addToInvoiceButton.addActionListener(this);
        System.out.println(foodItemDAO.getAllFoodItems().size());
        this.foodResultTable = new Table(TableMapper.foodItemToTable(foodItemDAO.getAllFoodItems()), TableColumn.FOOD_ITEM_COLUMN, TableConstant.FOOD_ITEM_COLUMN_WIDTHS);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
