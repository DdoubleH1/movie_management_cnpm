package org.example.view;

import org.example.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerHomeFrm extends JFrame implements ActionListener {
    private JPanel sellerHomeView;
    private JLabel sellerHomeTitle;
    private JButton sellFoodButton;
    private JButton sellComboButton;
    private JLabel welcomeTextField;
    private JButton sellTicketButton;


    public SellerHomeFrm(User user){
        setTitle("Seller Home");
        welcomeTextField.setText("Welcome " + user.getFullName());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(sellerHomeView);
        sellFoodButton.addActionListener(this);
        sellComboButton.addActionListener(this);
        sellTicketButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sellFoodButton) {
            (new SellFoodFrm()).setVisible(true);
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
