package org.example.view;

import org.example.constant.TableColumn;
import org.example.constant.TableConstant;
import org.example.dao.InvoiceDAO;
import org.example.mapper.TableMapper;
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
    //    private JTable ticketTable;
    private JTable foodTable;
    private JButton exchangePointButton;
    private JButton cancelButton;
    private JButton nextButton;
    private JPanel exchangeMembershipPointView;
    private JLabel customerInfoLabel;
    private Invoice invoice;
    private final InvoiceDAO invoiceDAO = new InvoiceDAO();
    private final ArrayList<FoodItemInvoiceDTO> exchangeFoodItemInvoice = new ArrayList<>();


    public ExchangeMembershipPointFrm(Invoice invoice) {
        this.invoice = invoice;
        initUI();
        bindingActionListener();
    }

    private void initUI() {
        setTitle("Exchange Membership Point");
        customerInfoLabel.setText(invoice.getCustomer().getFullName() + " - Membership point: " + invoice.getCustomer().getMembershipPoint() + " points");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(exchangeMembershipPointView);
        bindFoodItemInvoiceToTable(getFoodItemInvoices(invoice.getFoodItemInvoices()));
    }

    private void bindingActionListener() {
        exchangePointButton.addActionListener(this);
        cancelButton.addActionListener(this);
        nextButton.addActionListener(this);
    }

    private void bindFoodItemInvoiceToTable(ArrayList<FoodItemInvoiceDTO> foodItemInvoices) {

        Object[][] data = TableMapper.foodItemInvoiceToTable(foodItemInvoices);

        // Create a new table model
        DefaultTableModel model = new DefaultTableModel(data, TableColumn.FOOD_ITEM_INVOICE_COLUMN);

        // Set the model on foodResultTable
        this.foodTable.setModel(model);

        TableColumnModel columnModel = foodTable.getColumnModel();

        // Set the preferred width for each column
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(TableConstant.FOOD_ITEM_INVOICE_COLUMN_WIDTHS[i]);
        }

        this.foodTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    private ArrayList<FoodItemInvoiceDTO> getFoodItemInvoices(ArrayList<FoodItemInvoice> foodItemInvoices) {
        ArrayList<FoodItemInvoiceDTO> foodItemInvoiceDTOS = new ArrayList<>();
        for (FoodItemInvoice foodItemInvoice : foodItemInvoices) {
            FoodItemInvoiceDTO dto = invoiceDAO.getFoodItemInvoicesDTO(foodItemInvoice);
            if (foodItemInvoice.getQuantity() > 1) {
                for (int i = 1; i <= foodItemInvoice.getQuantity(); i++) {
                    FoodItemInvoiceDTO newDto = new FoodItemInvoiceDTO();
                    newDto.setId(dto.getId());
                    newDto.setSize(dto.getSize());
                    newDto.setFoodItemName(dto.getFoodItemName());
                    newDto.setPrice(dto.getPrice());
                    foodItemInvoiceDTOS.add(newDto);
                }
            } else {
                foodItemInvoiceDTOS.add(dto);
            }
        }
        return foodItemInvoiceDTOS;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exchangePointButton) {
            exchangePointButtonClicked();
        } else if (e.getSource() == cancelButton) {
//            cancelButtonClicked();
        } else if (e.getSource() == nextButton) {
//            nextButtonClicked();
        }
    }

    private void exchangePointButtonClicked() {
        int[] selectedRows = foodTable.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one food item to exchange membership point");
            return;
        }

        for (int selectedRow : selectedRows) {
            int foodItemId = (int) foodTable.getValueAt(selectedRow, 0);
            String size = (String) foodTable.getValueAt(selectedRow, 1);
            for(FoodItemInvoice foodItemInvoice: invoice.getFoodItemInvoices()){
                if(foodItemInvoice.getFoodItem().getId() == foodItemId && foodItemInvoice.getSize().equals(size)){
                }
            }
        }

    }


}
