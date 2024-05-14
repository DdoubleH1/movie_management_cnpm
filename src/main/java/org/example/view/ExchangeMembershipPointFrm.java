package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.InvoiceDAO;
import org.example.mapper.TableMapper;
import org.example.model.Customer;
import org.example.model.FoodItemInvoice;
import org.example.model.Invoice;
import org.example.model.User;
import org.example.model.dto.FoodItemInvoiceDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExchangeMembershipPointFrm extends JFrame implements ActionListener {
    private JTable ticketTable;
    private JTable foodTable;
    private JButton exchangePointButton;
    private JButton cancelButton;
    private JButton nextButton;
    private JPanel exchangeMembershipPointView;
    private User user;
    private Invoice invoice;
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();


    public ExchangeMembershipPointFrm(User user, Invoice invoice) {
        this.user = user;
        this.invoice = invoice;
        initUI();
        bindingActionListener();
    }

    private void initUI() {
        setTitle("Exchange Membership Point");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(exchangeMembershipPointView);
        bindFoodItemInvoiceToTable(invoiceDAO.getFoodItemInvoicesByInvoiceId(invoice));
    }

    private void bindingActionListener() {
        exchangePointButton.addActionListener(this);
        cancelButton.addActionListener(this);
        nextButton.addActionListener(this);
    }

    private void bindFoodItemInvoiceToTable(ArrayList<FoodItemInvoiceDTO> foodItemInvoices) {

        Object[][] data = TableMapper.foodItemInvoiceToTable(foodItemInvoices);

        // Create a new table model
        DefaultTableModel model = new DefaultTableModel(data, TableColumn.CUSTOMER_COLUMN);

        // Set the model on foodResultTable
        this.foodTable.setModel(model);

        TableColumnModel columnModel = foodTable.getColumnModel();

        // Set the preferred width for each column
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(TableConstant.CUSTOMER_COLUMN_WIDTHS[i]);
        }

        this.foodTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
