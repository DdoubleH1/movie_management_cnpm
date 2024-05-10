package org.example.view;

import org.example.dao.FoodItemDAO;
import org.example.model.FoodItem;

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

        String[] columnNames = {"ID", "Name", "Type"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        // set size for cloumns
        foodResultTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        foodResultTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        foodResultTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        //center align
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        ArrayList<FoodItem> foodItems = foodItemDAO.getAllFoodItems();
        foodItems.forEach(foodItem -> {
            model.addRow(new Object[]{foodItem.getId(), foodItem.getName(), foodItem.getType()});
        });

        foodResultTable.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
