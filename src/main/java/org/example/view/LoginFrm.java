package org.example.view;

import org.example.dao.UserDAO;
import org.example.model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrm extends JFrame implements ActionListener {
    private JPanel loginView;
    private JLabel loginTitle;
    private JLabel usernameTitle;
    private JTextField usernameTextField;
    private JLabel passwordTitle;
    private JTextField passwordTextField;
    private JButton loginButton;

    public LoginFrm(){
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(loginView);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            User user = new User();
            user.setUsername(usernameTextField.getText());
            user.setPassword(passwordTextField.getText());

            UserDAO userDAO = new UserDAO();
            if(userDAO.checkLogin(user)){
                JOptionPane.showMessageDialog(this, "Login success");
                (new SellerHomeFrm(user)).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Login failed");
            }
        }
    }
}
