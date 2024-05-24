package org.example.dao;

import org.example.model.FoodItemInvoice;
import org.example.model.Invoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InvoiceDAO extends DAO {

    public InvoiceDAO() {
        super();
    }

    //update invoice
    public void save(Invoice invoice) {
        String query = "INSERT INTO tblinvoice (payDate, customerID, userID) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, invoice.getPayDate());
            ps.setInt(2, invoice.getCustomer().getId());
            ps.setInt(3, invoice.getUser().getId());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //add food item invoice
        for (FoodItemInvoice foodItemInvoice : invoice.getFoodItemInvoices()) {
            String queryFoodItemInvoice = "INSERT INTO tblfooditeminvoice (invoiceID, foodItemID, size, quantity, exchangeQuantity, unitPrice) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(queryFoodItemInvoice);
                ps.setInt(1, invoice.getId());
                ps.setInt(2, foodItemInvoice.getFoodItem().getId());
                ps.setString(3, foodItemInvoice.getSize());
                ps.setInt(4, foodItemInvoice.getQuantity());
                ps.setInt(5, foodItemInvoice.getExchangeQuantity());
                ps.setInt(6, foodItemInvoice.getUnitPrice());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //update food item detail quantity
        for (FoodItemInvoice foodItemInvoice : invoice.getFoodItemInvoices()) {
            String queryFoodItemDetail = "UPDATE tblfooditemdetail SET totalQuantity = totalQuantity - ? WHERE foodItemID = ? AND size = ?";
            try {
                PreparedStatement ps = con.prepareStatement(queryFoodItemDetail);
                ps.setInt(1, foodItemInvoice.getQuantity());
                ps.setInt(2, foodItemInvoice.getFoodItem().getId());
                ps.setString(3, foodItemInvoice.getSize());
                ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
