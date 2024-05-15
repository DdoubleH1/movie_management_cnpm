package org.example.dao;

import org.example.model.FoodItemInvoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FoodItemInvoiceDAO extends DAO {
    public FoodItemInvoiceDAO() {
        super();
    }

//    public void addFoodItemInvoice(FoodItemInvoice foodItemInvoice) {
//        String query = "INSERT INTO tblfooditeminvoice (invoiceID, foodItemID, size, quantity) VALUES (?, ?, ?, ?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, foodItemInvoice.getInvoiceId());
//            ps.setInt(2, foodItemInvoice.getFoodItemId());
//            ps.setString(3, foodItemInvoice.getSize());
//            ps.setInt(4, foodItemInvoice.getQuantity());
//            ps.executeUpdate();
//
//            ResultSet generatedKeys = ps.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                foodItemInvoice.setId(generatedKeys.getInt(1));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}