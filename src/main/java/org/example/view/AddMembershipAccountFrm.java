package org.example.view;

import org.example.dao.CustomerDAO;
import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMembershipAccountFrm extends JFrame implements ActionListener {
    private JPanel addMembershipAccountView;
    private JTextField fullNameTextField;
    private JTextField ageTextField;
    private JTextField phoneNumberTextField;
    private JTextField addressTextField;
    private JButton cancelButton;
    private JButton addButton;
    private final CustomerDAO customerDAO = new CustomerDAO();
    private Customer customer;
    private User user;
    private Invoice invoice;

    public AddMembershipAccountFrm(Invoice invoice) {
        this.invoice = invoice;
        initUI();
        bindingActionListener();
    }

    private void initUI() {
        setTitle("Add Membership Account");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(addMembershipAccountView);
    }

    private void bindingActionListener() {
        cancelButton.addActionListener(this);
        addButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            (new MembershipAccountFrm(invoice)).setVisible(true);
            this.dispose();
        } else if (e.getSource() == addButton) {
            addButtonClick();
        }
    }

    private void addButtonClick() {
        String fullName = fullNameTextField.getText();
        String age = ageTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String address = addressTextField.getText();
        Customer customer = new Customer(fullName, address, Integer.parseInt(age), phoneNumber, 0);
        if (fullName.isEmpty() || age.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
        } else {
            if(!customerDAO.addCustomer(customer)){
                JOptionPane.showMessageDialog(this, "Customer already exists");
            }
            else{
                JOptionPane.showMessageDialog(this, "Add customer successfully");
                invoice.setCustomer(customer);
                (new ExchangeMembershipPointFrm(invoice)).setVisible(true);
                this.dispose();
            }
        }
    }
}
