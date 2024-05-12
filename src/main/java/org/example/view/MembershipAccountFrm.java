package org.example.view;

import org.example.model.Invoice;
import org.example.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MembershipAccountFrm extends JFrame implements ActionListener {
    private JPanel MembershipAccountView;
    private JTextField searchTexfield;
    private JComboBox searchOptionComboBox;
    private JButton searchButton;
    private JTable customerResultTable;
    private JButton exchangePointButton;
    private JButton nextButton;
    private JButton addNewAccountButton;
    private User user;
    private Invoice invoice;

    public MembershipAccountFrm(User user, Invoice invoice){
        this.user = user;
        this.invoice = invoice;
        initUI();
        bindingActionListener();
    }

    private void initUI(){
        setTitle("Membership Account");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(MembershipAccountView);
    }

    private void bindingActionListener(){
        searchButton.addActionListener(this);
        exchangePointButton.addActionListener(this);
        nextButton.addActionListener(this);
        addNewAccountButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
