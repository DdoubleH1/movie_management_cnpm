package org.example.view;

import org.example.constant.SearchOption;
import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.CustomerDAO;
import org.example.mapper.TableMapper;
import org.example.model.Customer;
import org.example.model.FoodItem;
import org.example.model.Invoice;
import org.example.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MembershipAccountFrm extends JFrame implements ActionListener {
    private JPanel MembershipAccountView;
    private JTextField searchTexfield;
    private JComboBox<SearchOption> searchOptionComboBox;
    private JButton searchButton;
    private JTable customerResultTable;
    private JButton exchangePointButton;
    private JButton nextButton;
    private JButton addNewAccountButton;
    private User user;
    private Invoice invoice;
    private SearchOption searchOption = SearchOption.FULL_NAME;
    private final CustomerDAO customerDAO = new CustomerDAO();

    public MembershipAccountFrm(User user, Invoice invoice) {
        this.user = user;
        this.invoice = invoice;
        initUI();
        bindingActionListener();
    }

    private void initUI() {
        setTitle("Membership Account");
        setSize(1000, 800);
        searchOptionComboBox.addItem(SearchOption.FULL_NAME);
        searchOptionComboBox.addItem(SearchOption.PHONE_NUMBER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(MembershipAccountView);
    }

    private void bindingActionListener() {
        searchButton.addActionListener(this);
        searchOptionComboBox.addActionListener(this);
        exchangePointButton.addActionListener(this);
        nextButton.addActionListener(this);
        addNewAccountButton.addActionListener(this);
    }

    private void bindCustomersToTable(ArrayList<Customer> customers) {
        // Convert the food items to a 2D array
        Object[][] data = TableMapper.customerToTable(customers);

        // Create a new table model
        DefaultTableModel model = new DefaultTableModel(data, TableColumn.CUSTOMER_COLUMN);

        // Set the model on foodResultTable
        this.customerResultTable.setModel(model);

        TableColumnModel columnModel = customerResultTable.getColumnModel();

        // Set the preferred width for each column
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(TableConstant.CUSTOMER_COLUMN_WIDTHS[i]);
        }

        this.customerResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchOptionComboBox) {
            searchOptionComboBoxClick();
        } else if (e.getSource() == searchButton) {
            searchButtonClicked();
        } else if (e.getSource() == exchangePointButton) {

        } else if (e.getSource() == nextButton) {

        } else if (e.getSource() == addNewAccountButton) {
            addNewAccountButtonClick();
        }

    }

    private void searchOptionComboBoxClick() {
        this.searchOption = (SearchOption) searchOptionComboBox.getSelectedItem();
    }

    private void searchButtonClicked() {

        String keyword = searchTexfield.getText();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a keyword to search");
        } else {
            bindCustomersToTable(customerDAO.searchCustomer(keyword, this.searchOption));
        }

    }

    private void addNewAccountButtonClick() {
        AddMembershipAccountFrm addMembershipAccountFrm = new AddMembershipAccountFrm(this.user, this.invoice);
        addMembershipAccountFrm.setVisible(true);
    }

}

