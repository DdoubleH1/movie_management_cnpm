package org.example.dao;

import org.example.model.FoodItemDetail;
import org.example.model.FoodItemInvoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FoodItemDetailDAO extends DAO {
    public FoodItemDetailDAO() {
        super();
    }

    //check if quantity is enough
    public boolean checkQuantity(FoodItemInvoice foodItemInvoice) {
        String query = "SELECT totalQuantity FROM tblfooditemdetail WHERE foodItemID = ? and size = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, foodItemInvoice.getFoodItem().getId());
            ps.setString(2, foodItemInvoice.getSize());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalQuantity = rs.getInt("totalQuantity");
                if (totalQuantity < foodItemInvoice.getQuantity()) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
