package org.example.view;

import org.example.constant.SearchOption;
import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.CustomerDAO;
import org.example.mapper.TableMapper;
import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.view.component.Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MembershipAccountFrm extends JFrame implements ActionListener {
    private JPanel MembershipAccountView;
    private JTextField searchTexfield;
    private JComboBox<SearchOption> searchOptionComboBox;
    private JButton searchButton;
    private Table customerResultTable;
    private JButton nextButton;
    private JButton addNewAccountButton;
    private JButton cancelButton;
    private Invoice invoice;
    private SearchOption searchOption = SearchOption.FULL_NAME;
    ArrayList<Customer> searchCustomers = new ArrayList<>();
    private final CustomerDAO customerDAO = new CustomerDAO();

    public MembershipAccountFrm(Invoice invoice) {
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
        nextButton.addActionListener(this);
        addNewAccountButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchOptionComboBox) {
            searchOptionComboBoxClick();
        } else if (e.getSource() == searchButton) {
            searchButtonClicked();
        } else if (e.getSource() == nextButton) {
            nextButtonClick();
        } else if (e.getSource() == addNewAccountButton) {
            addNewAccountButtonClick();
        } else if (e.getSource() == cancelButton) {
            (new SellFoodFrm(this.invoice)).setVisible(true);
            this.dispose();
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
            searchCustomers = customerDAO.searchCustomer(keyword, this.searchOption);
            customerResultTable.updateTableData(TableMapper.customerToTable(searchCustomers), TableColumn.CUSTOMER_COLUMN);
        }
    }

    private void nextButtonClick() {
        int selectedRow = customerResultTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to continue");
        } else {
            int customerId = (int) customerResultTable.getValueAt(selectedRow, 0);
            for (Customer customer : searchCustomers) {
                if (customer.getId() == customerId) {
                    this.invoice.setCustomer(customer);
                    break;
                }
            }
            ExchangeMembershipPointFrm exchangeMembershipPointFrm = new ExchangeMembershipPointFrm(this.invoice);
            exchangeMembershipPointFrm.setVisible(true);
            this.dispose();
        }
    }

    private void addNewAccountButtonClick() {
        AddMembershipAccountFrm addMembershipAccountFrm = new AddMembershipAccountFrm(this.invoice);
        addMembershipAccountFrm.setVisible(true);
        this.dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        customerResultTable = new Table(TableMapper.customerToTable(new ArrayList<>()), TableColumn.CUSTOMER_COLUMN, TableConstant.CUSTOMER_COLUMN_WIDTHS, ListSelectionModel.SINGLE_SELECTION);
    }
}

