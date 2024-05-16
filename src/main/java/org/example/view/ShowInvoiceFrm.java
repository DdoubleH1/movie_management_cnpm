package org.example.view;

import org.example.dao.InvoiceDAO;
import org.example.model.Invoice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowInvoiceFrm extends JFrame implements ActionListener {
    private JPanel ShowInvoiceView;
    private JTable table1;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextArea invoiceInfo;
    private Invoice invoice;

    public ShowInvoiceFrm(Invoice invoice) {
        this.invoice = invoice;
        initUI();
        bindingActionListener();
    }

    private void initUI() {
        setTitle("Show Invoice");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(ShowInvoiceView);
    }

    private void bindingActionListener() {
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
