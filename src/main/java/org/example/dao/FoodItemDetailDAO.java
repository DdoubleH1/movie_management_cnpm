package org.example.dao;

import org.example.model.FoodItemInvoice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FoodItemDetailDAO extends DAO {
    public FoodItemDetailDAO() {
        super();
    }


    public String getFoodItemName(int foodItemId) {
        String query = "SELECT name FROM tblfooditem WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, foodItemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //update food item detail when user buy
    public synchronized boolean updateFoodItemDetail(FoodItemInvoice foodItemInvoice) {
        boolean result = false;
        if (checkQuantity(foodItemInvoice)) {
            String query = "UPDATE tblfooditemdetail SET totalQuantity = totalQuantity - ? WHERE foodItemID = ?";
            try {
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, foodItemInvoice.getQuantity());
                ps.setInt(2, foodItemInvoice.getFoodItemId());
                ps.executeUpdate();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new RuntimeException("Remaining quantity is not enough");
        }
        return result;
    }

    //check if quantity is enough
    public boolean checkQuantity(FoodItemInvoice foodItemInvoice) {
        String query = "SELECT totalQuantity FROM tblfooditemdetail WHERE foodItemID = ? and size = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, foodItemInvoice.getFoodItemId());
            ps.setString(2, foodItemInvoice.getSize());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalQuantity = rs.getInt("totalQuantity");
                System.out.println(totalQuantity);
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
