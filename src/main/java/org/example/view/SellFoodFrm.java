package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.FoodItemDAO;
import org.example.dao.FoodItemDetailDAO;
import org.example.mapper.TableMapper;
import org.example.model.*;
import org.example.view.component.Table;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SellFoodFrm extends JFrame implements ActionListener {
    private JPanel sellFoodView;
    private JTextField foodNameTextField;
    private JButton searchButton;
    private Table foodResultTable;
    private JButton processPaymentButton;
    private JTextField sizeTextField;
    private JTextField quantityTextField;
    private JButton addToInvoiceButton;
    private ArrayList<FoodItem> searchFoodItem;
    private final Invoice invoice;
    private int foodItemInvoiceId = 1;

    private final ArrayList<FoodItemInvoice> foodItemInvoices = new ArrayList<>();
    private final FoodItemDAO foodItemDAO = new FoodItemDAO();
    private final FoodItemDetailDAO foodItemDetailDAO = new FoodItemDetailDAO();

    public SellFoodFrm(Invoice invoice) {
        if(invoice.getFoodItemInvoices() != null) {
            foodItemInvoices.addAll(invoice.getFoodItemInvoices());
            foodItemInvoiceId = foodItemInvoices.size() + 1;
        }
        initUI();
        bindingActionListener();
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
        if (foodName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a food name to search");
        } else {
            searchFoodItem = foodItemDAO.searchFoodItem(foodName);
            foodResultTable.updateTableData(TableMapper.foodItemToTable(searchFoodItem), TableColumn.FOOD_ITEM_COLUMN);
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
        for (FoodItem fi : searchFoodItem) {
            if (fi.getId() == foodItemId) {
                foodItem = fi;
                break;
            }
        }

        try {
            FoodItemInvoice newFoodItemInvoice = getFoodItemInvoice(foodItem);
            if (foodItemDetailDAO.checkQuantity(newFoodItemInvoice)) {
                for (FoodItemInvoice existingFoodItemInvoice : foodItemInvoices) {
                    if (existingFoodItemInvoice.equals(newFoodItemInvoice)) {
                        existingFoodItemInvoice.setQuantity(existingFoodItemInvoice.getQuantity() + newFoodItemInvoice.getQuantity());
                        JOptionPane.showMessageDialog(this, "Food item quantity updated in invoice successfully");
                        return;
                    }
                }
                newFoodItemInvoice.setId(foodItemInvoiceId++);
                foodItemInvoices.add(newFoodItemInvoice);
                JOptionPane.showMessageDialog(this, "Food item added to invoice successfully");
                sizeTextField.setText("");
                quantityTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Remaining quantity is not enough");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity must be a number");
        }
    }

    private FoodItemInvoice getFoodItemInvoice(FoodItem foodItem) {
        int quantity = Integer.parseInt(quantityTextField.getText());
        String size = sizeTextField.getText();
        int unitPrice = 0;
        for(int j = 0; j < foodItem.getFoodItemDetailList().size(); j++) {
            if(foodItem.getFoodItemDetailList().get(j).getSize().equals(size)) {
                unitPrice = foodItem.getFoodItemDetailList().get(j).getPrice();
                break;
            }
        }
        System.out.println(unitPrice);
        return new FoodItemInvoice(size, quantity, 0, foodItem, unitPrice);
    }

    private void processPaymentClicked() {
        if(foodItemInvoices.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one food item to the invoice");
            return;
        }
        System.out.println(foodItemInvoices);
        invoice.setFoodItemInvoices(foodItemInvoices);
        (new MembershipAccountFrm(invoice)).setVisible(true);
        this.dispose();
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        foodResultTable = new Table(TableMapper.foodItemToTable(new ArrayList<>()), TableColumn.FOOD_ITEM_COLUMN, TableConstant.FOOD_ITEM_COLUMN_WIDTHS, ListSelectionModel.SINGLE_SELECTION);
    }
}
