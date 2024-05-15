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


    //update food item detail when user buy
    public boolean updateFoodItemDetail(FoodItemInvoice foodItemInvoice) {
        boolean result = false;
        String query = "UPDATE tblfooditemdetail SET totalQuantity = totalQuantity - ? WHERE foodItemID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, foodItemInvoice.getQuantity());
            ps.setInt(2, foodItemInvoice.getFoodItem().getId());
            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<FoodItemDetail> getFoodItemDetailByFoodItemId(int foodItemId) {
        ArrayList<FoodItemDetail> foodItemDetails = new ArrayList<>();
        String query = "SELECT * FROM tblfooditemdetail WHERE foodItemID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, foodItemId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FoodItemDetail foodItemDetail = new FoodItemDetail();
                foodItemDetail.setId(rs.getInt("id"));
                foodItemDetail.setSize(rs.getString("size"));
                foodItemDetail.setTotalQuantity(rs.getInt("totalQuantity"));
                foodItemDetail.setPrice(rs.getInt("price"));
                foodItemDetails.add(foodItemDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodItemDetails;
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
