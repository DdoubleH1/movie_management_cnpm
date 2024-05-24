package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.mapper.TableMapper;
import org.example.model.FoodItemInvoice;
import org.example.model.Invoice;
import org.example.view.component.Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ExchangeMembershipPointFrm extends JFrame implements ActionListener {
    //    private JTable ticketTable;
    private Table foodTable;
    private JButton exchangePointButton;
    private JButton cancelButton;
    private JButton nextButton;
    private JPanel exchangeMembershipPointView;
    private JLabel customerInfoLabel;
    private final Invoice invoice;
    private final ArrayList<FoodItemInvoice> foodItemInvoices = new ArrayList<>();
    private int totalExchangePoint;
    public ExchangeMembershipPointFrm(Invoice invoice) {
        this.invoice = invoice;
        initUI();
        bindingActionListener();
        resetExchangeFoodItemInvoice();
    }

    private void initUI() {
        setTitle("Exchange Membership Point");
        customerInfoLabel.setText(invoice.getCustomer().getFullName() + " - Membership point: " + invoice.getCustomer().getMembershipPoint() + " points");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(exchangeMembershipPointView);
        foodItemInvoices.addAll(invoice.getFoodItemInvoices());
        foodTable.updateTableData(TableMapper.foodItemInvoiceToTable(foodItemInvoices, true), TableColumn.FOOD_ITEM_INVOICE_COLUMN);
    }

    private void bindingActionListener() {
        foodTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = foodTable.rowAtPoint(e.getPoint());
                if (selectedRow != -1) {
                    int foodItemInvoiceId = (int) foodTable.getValueAt(selectedRow, 0);
                    FoodItemInvoice foodItemInvoice = foodItemInvoices.stream().filter(f -> f.getId() == foodItemInvoiceId).findFirst().orElse(null);
                    showExchangePointDialog(foodItemInvoice);
                }
            }
        });
        exchangePointButton.addActionListener(this);
        cancelButton.addActionListener(this);
        nextButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exchangePointButton) {
            exchangePointButtonClicked();
        } else if (e.getSource() == nextButton) {
            nextButtonClicked();
        } else if (e.getSource() == cancelButton) {
            cancelButtonClicked();
        }
    }

    private void showExchangePointDialog(FoodItemInvoice foodItemInvoice) {
        JLabel exchangePointLabel = new JLabel("Enter food quantity to exchange: ");
        JTextField exchangeQuantityTextField = new JTextField();
        Object[] message = {
                exchangePointLabel, exchangeQuantityTextField
        };
        Object[] options = {"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(this, message, "Exchange Membership Point", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (option == JOptionPane.OK_OPTION) {
            // check if there is enough membership point to exchange
            int exchangeQuantity = Integer.parseInt(exchangeQuantityTextField.getText());
            int exchangePoint = exchangeQuantity * foodItemInvoice.getUnitPrice();
            if(invoice.getCustomer().getMembershipPoint() < exchangePoint){
                JOptionPane.showMessageDialog(this, "Not enough membership point to exchange");
                return;
            }
            totalExchangePoint += exchangePoint;
            foodItemInvoice.setExchangeQuantity(Integer.parseInt(exchangeQuantityTextField.getText()));
        }
    }



    private void resetExchangeFoodItemInvoice() {
        for (FoodItemInvoice foodItemInvoice : foodItemInvoices) {
            foodItemInvoice.setExchangeQuantity(0);
        }
    }


    private void exchangePointButtonClicked() {
        int[] selectedRows = foodTable.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one food item to exchange membership point");
            return;
        }
        
        for (int selectedRow : selectedRows) {
            // loop through selected rows and count the number of food item to exchange
            int foodItemInvoiceId = (int) foodTable.getValueAt(selectedRow, 0);
            int foodItemPrice = (int) foodTable.getValueAt(selectedRow, 3);
        }

        if (invoice.getCustomer().getMembershipPoint() < totalExchangePoint) {
            JOptionPane.showMessageDialog(this, "Not enough membership point to exchange");
            return;
        }


        System.out.println((totalExchangePoint));
        invoice.setTotalPrice(calcTotalPriceBeforeExchange(foodItemInvoices) - totalExchangePoint);

        invoice.setFoodItemInvoices(foodItemInvoices);
        (new ShowInvoiceFrm(invoice)).setVisible(true);
        this.dispose();

    }

    private void cancelButtonClicked() {
        (new MembershipAccountFrm(invoice)).setVisible(true);
        this.dispose();
    }

    private void nextButtonClicked() {
        resetExchangeFoodItemInvoice();
        invoice.setTotalPrice(calcTotalPriceBeforeExchange(foodItemInvoices));
        (new ShowInvoiceFrm(this.invoice)).setVisible(true);
        this.dispose();
    }

    private int calcTotalPriceBeforeExchange(ArrayList<FoodItemInvoice> foodItemInvoices) {
        int totalPriceBeforeExchange = 0;
        for (FoodItemInvoice foodItemInvoice : foodItemInvoices) {
            if (foodItemInvoice.getQuantity() > 0) {
                totalPriceBeforeExchange += foodItemInvoice.getUnitPrice() * foodItemInvoice.getQuantity();
            } else {
                totalPriceBeforeExchange += foodItemInvoice.getUnitPrice();
            }
        }
        return totalPriceBeforeExchange;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        foodTable = new Table(TableMapper.foodItemInvoiceToTable(new ArrayList<>(), true), TableColumn.FOOD_ITEM_INVOICE_COLUMN, TableConstant.FOOD_ITEM_INVOICE_COLUMN_EXCHANGE_WIDTHS, ListSelectionModel.SINGLE_SELECTION);
    }
}
