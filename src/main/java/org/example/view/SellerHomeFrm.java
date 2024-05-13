package org.example.view;

import org.example.dao.InvoiceDAO;
import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SellerHomeFrm extends JFrame implements ActionListener {
    private JPanel sellerHomeView;
    private JLabel sellerHomeTitle;
    private JButton sellFoodButton;
    private JButton sellComboButton;
    private JLabel welcomeTextField;
    private User user;
    private Customer customer;
    private Invoice invoice;
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();

    public SellerHomeFrm(User user){
        this.user = user;
        initUI();
        bindingActionListener();
        initInvoice();
    }

    private void initInvoice() {
        invoice = new Invoice();
        // Set the pay date to the current date and format int pattern yyyy-MM-dd HH:mm:ss
        invoice.setPayDate(getCurrentDate());
        invoice.setUserId(this.user.getId());
        try{
            invoiceDAO.addInvoice(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        setTitle("Seller Home");
        welcomeTextField.setText("Welcome " + this.user.getFullName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(sellerHomeView);
    }

    private void bindingActionListener() {
        sellFoodButton.addActionListener(this);
        sellComboButton.addActionListener(this);
    }

    private String getCurrentDate() {
        // Create a new Date object for the current date and time
        Date payDate = new Date();

        // Create a SimpleDateFormat object for the desired format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(payDate);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sellFoodButton) {
            (new SellFoodFrm(this.user, this.invoice)).setVisible(true);
            this.dispose();
        }
//        else if (e.getSource() == sellComboButton) {
//            (new SellComboFrm()).setVisible(true);
//            this.dispose();
//        }
//        else if (e.getSource() == sellTicketButton) {
//            (new SellTicketFrm()).setVisible(true);
//            this.dispose();
//        }
    }
}
