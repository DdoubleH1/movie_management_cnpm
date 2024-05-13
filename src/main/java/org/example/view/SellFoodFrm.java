package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.FoodItemDAO;
import org.example.dao.FoodItemDetailDAO;
import org.example.dao.FoodItemInvoiceDAO;
import org.example.mapper.TableMapper;
import org.example.model.*;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SellFoodFrm extends JFrame implements ActionListener {
    private JPanel sellFoodView;
    private JTextField foodNameTextField;
    private JButton searchButton;
    private JTable foodResultTable;
    private JButton processPaymentButton;
    private JTextField sizeTextField;
    private JTextField quantityTextField;
    private JButton addToInvoiceButton;
    private Invoice invoice;
    private User user;
    private final FoodItemDAO foodItemDAO = new FoodItemDAO();
    private final FoodItemInvoiceDAO foodItemInvoiceDAO = new FoodItemInvoiceDAO();
    private final FoodItemDetailDAO foodItemDetailDAO = new FoodItemDetailDAO();

    public SellFoodFrm(User user, Invoice invoice) {
        initUI();
        bindingActionListener();
        this.user = user;
        this.invoice = invoice;
    }


    private void initUI() {
        setTitle("Sell Food");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(sellFoodView);
        bindFoodItemsToTable(foodItemDAO.getAllFoodItems());
    }

    private void bindingActionListener() {
        searchButton.addActionListener(this);
        processPaymentButton.addActionListener(this);
        addToInvoiceButton.addActionListener(this);
    }

    private void bindFoodItemsToTable(ArrayList<FoodItem> foodItems) {
        // Convert the food items to a 2D array
        Object[][] data = TableMapper.foodItemToTable(foodItems);

        // Create a new table model
        DefaultTableModel model = new DefaultTableModel(data, TableColumn.FOOD_ITEM_COLUMN);

        // Set the model on foodResultTable
        this.foodResultTable.setModel(model);

        TableColumnModel columnModel = foodResultTable.getColumnModel();

        // Set the preferred width for each column
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(TableConstant.FOOD_ITEM_COLUMN_WIDTHS[i]);
        }

        this.foodResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            searchClicked();
        } else if (e.getSource() == addToInvoiceButton) {
            addToInvoiceClicked();

        } else if (e.getSource() == processPaymentButton) {
            processPaymentClicked();
        }
    }

    private void searchClicked() {
        String foodName = foodNameTextField.getText();
        if(foodName.isEmpty()){
           JOptionPane.showMessageDialog(this, "Please enter a food name to search");
        }
        else{
            bindFoodItemsToTable(foodItemDAO.searchFoodItem(foodName));
        }
    }

    private void addToInvoiceClicked() {
        int selectedRow = foodResultTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a food item to add to the invoice");
            return;
        }

        int foodItemId = Integer.parseInt(foodResultTable.getValueAt(selectedRow, 0).toString());
        int quantity = Integer.parseInt(quantityTextField.getText());
        String size = sizeTextField.getText();
        FoodItemInvoice foodItemInvoice = new FoodItemInvoice(size, quantity, foodItemId, invoice.getId());
        try {
            if (foodItemDetailDAO.updateFoodItemDetail(foodItemInvoice)) {
                foodItemInvoiceDAO.addFoodItemInvoice(foodItemInvoice);
                JOptionPane.showMessageDialog(this, "Food item added to invoice successfully");
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    private void processPaymentClicked() {
        (new MembershipAccountFrm(user, invoice)).setVisible(true);
        this.dispose();
    }
}
