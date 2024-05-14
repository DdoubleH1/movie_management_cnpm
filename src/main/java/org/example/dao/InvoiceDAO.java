package org.example.dao;

import org.example.model.FoodItem;
import org.example.model.FoodItemInvoice;
import org.example.model.Invoice;
import org.example.model.dto.FoodItemInvoiceDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InvoiceDAO extends DAO {

    public InvoiceDAO() {
        super();
    }

    public void addInvoice(Invoice invoice) {
        String query = "INSERT INTO tblinvoice (payDate, userID) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, invoice.getPayDate());
            ps.setInt(2, invoice.getUserId());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Invoice updateInvoice(Invoice invoice) {
        String query = "UPDATE tblinvoice SET customerID = ? WHERE payDate = ? AND userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, invoice.getCustomerId());
            ps.setString(2, invoice.getPayDate());
            ps.setInt(3, invoice.getUserId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public ArrayList<FoodItemInvoiceDTO> getFoodItemInvoicesByInvoiceId(Invoice invoice) {
        ArrayList<FoodItemInvoiceDTO> foodItemInvoices = new ArrayList<>();
        // Get all food item invoices by invoice id and replace food item id with food item name

        String query = "SELECT * FROM tblfooditeminvoice " +
                "JOIN tblfooditem ON tblfooditeminvoice.foodItemID = tblfooditem.id " +
                "JOIN tblfooditemdetail ON tblfooditeminvoice.foodItemID = tblfooditemdetail.foodItemID AND tblfooditeminvoice.size = tblfooditemdetail.size "+
                "WHERE invoiceID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, invoice.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FoodItemInvoiceDTO foodItemInvoice = new FoodItemInvoiceDTO();
                foodItemInvoice.setId(rs.getInt("id"));
                foodItemInvoice.setFoodItemName(rs.getString("name"));
                foodItemInvoice.setSize(rs.getString("size"));
                foodItemInvoice.setPrice(rs.getInt("price"));
                if (rs.getInt("quantity") > 1) {
                    for (int i = 0; i < rs.getInt("quantity"); i++) {
                        foodItemInvoices.add(foodItemInvoice);
                    }
                } else {
                    foodItemInvoices.add(foodItemInvoice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();


        }
        return foodItemInvoices;
    }
}