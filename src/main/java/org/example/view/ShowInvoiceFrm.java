package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.FoodItemDetailDAO;
import org.example.dao.InvoiceDAO;
import org.example.mapper.TableMapper;
import org.example.model.FoodItemInvoice;
import org.example.model.Invoice;
import org.example.view.component.Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowInvoiceFrm extends JFrame implements ActionListener {
    private JPanel ShowInvoiceView;
    private Table foodTable;
    private JButton confirmButton;
    private JButton cancelButton;
    private JTextArea invoiceInfo;
    private Invoice invoice;
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();
    private final FoodItemDetailDAO foodItemDetailDAO = new FoodItemDetailDAO();

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
        foodTable.updateTableData(TableMapper.foodItemInvoiceToTable(invoice.getFoodItemInvoices(), false), TableColumn.FOOD_ITEM_INVOICE_COLUMN);
        bindInvoiceInfo();

    }

    private void bindingActionListener() {
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    private void bindInvoiceInfo() {
        String info = "Customer name: " + invoice.getCustomer().getFullName() + "\n" +
                "Customer phone number: " + invoice.getCustomer().getPhoneNumber() + "\n" +
                "Customer address: " + invoice.getCustomer().getAddress() + "\n" +
                "Total amount: " + invoice.getTotalPrice() + " (VND)" + "\n" +
                "Payment Date: " + invoice.getPayDate() + "\n" +
                "The seller: " + invoice.getUser().getFullName();
        invoiceInfo.setText(info);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            confirmButtonClick();
        } else if (e.getSource() == cancelButton) {
            (new ExchangeMembershipPointFrm(invoice)).setVisible(true);
            this.dispose();
        }
    }

    private void confirmButtonClick() {
        for (FoodItemInvoice foodItemInvoice : invoice.getFoodItemInvoices()) {
            if (!foodItemDetailDAO.checkQuantity(foodItemInvoice)) {
                showDialog(foodItemInvoice);
                return;
            }
        }
        invoiceDAO.save(invoice);
        JOptionPane.showMessageDialog(this, "Confirm successfully");
        (new SellerHomeFrm(invoice.getUser())).setVisible(true);
        this.dispose();
    }

    private void showDialog(FoodItemInvoice foodItemInvoice) {
        JLabel label = new JLabel("The quantity of " + foodItemInvoice.getFoodItem().getName() + " is not enough");
        Object[] message = {
                label
        };
        Object[] options = {"OK"};
        int option = JOptionPane.showOptionDialog(this, message, "Notification", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (option == JOptionPane.YES_OPTION) {
            (new SellerHomeFrm(invoice.getUser())).setVisible(true);
            this.dispose();
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        foodTable = new Table(TableMapper.foodItemInvoiceToTable(new ArrayList<>(), false), TableColumn.FOOD_ITEM_INVOICE_COLUMN, TableConstant.FOOD_ITEM_INVOICE_COLUMN_WIDTHS, ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
}
