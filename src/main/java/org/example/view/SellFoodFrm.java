package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.FoodItemDAO;
import org.example.dao.FoodItemDetailDAO;
import org.example.mapper.TableMapper;
import org.example.model.*;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
    private ArrayList<FoodItem> searchFoodItem;
    private Invoice invoice;
    private User user;
    private int foodItemInvoiceId = 1;

    private final ArrayList<FoodItemInvoice> foodItemInvoices = new ArrayList<>();
    private final FoodItemDAO foodItemDAO = new FoodItemDAO();
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
            searchFoodItem = foodItemDAO.searchFoodItem(foodName);
            bindFoodItemsToTable(searchFoodItem);
        }
    }

    private void addToInvoiceClicked() {
        int selectedRow = foodResultTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a food item to add to the invoice");
            return;
        }
        FoodItem foodItem = new FoodItem();
        int foodItemId = Integer.parseInt(foodResultTable.getValueAt(selectedRow, 0).toString());
        for(FoodItem fi: searchFoodItem){
            if(fi.getId() == foodItemId){
                foodItem = fi;
                break;
        }}

        try{
            int quantity = Integer.parseInt(quantityTextField.getText());
            String size = sizeTextField.getText();
            FoodItemInvoice foodItemInvoice = new FoodItemInvoice(size, quantity, 0, foodItem);
            if(foodItemDetailDAO.checkQuantity(foodItemInvoice)){
                foodItemInvoice.setId(foodItemInvoiceId++);
                foodItemInvoices.add(foodItemInvoice);
                JOptionPane.showMessageDialog(this, "Food item added to invoice successfully");
                sizeTextField.setText("");
                quantityTextField.setText("");
            }
            else{
                JOptionPane.showMessageDialog(this, "Remaining quantity is not enough");
            }
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Quantity must be a number");
        }

    }

    private void processPaymentClicked() {
        invoice.setFoodItemInvoices(foodItemInvoices);
        (new MembershipAccountFrm(invoice)).setVisible(true);
        this.dispose();
    }
}
