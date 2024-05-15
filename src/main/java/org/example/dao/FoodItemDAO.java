package org.example.dao;

import org.example.model.FoodItem;
import org.example.model.FoodItemDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodItemDAO extends DAO {
    public FoodItemDAO() {
        super();
    }

//    public ArrayList<FoodItem> getAllFoodItems() {
//        ArrayList<FoodItem> result = new ArrayList<>();
//        String sql = "SELECT * FROM tblfooditem";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                FoodItem foodItem = new FoodItem(rs.getInt("id"), rs.getString("name"), rs.getString("type"));
//                result.add(foodItem);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }


    public ArrayList<FoodItem> searchFoodItem(String foodName) {
        ArrayList<FoodItem> result = new ArrayList<>();
        String sql = "SELECT * FROM tblfooditem WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + foodName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FoodItem foodItem = new FoodItem(rs.getInt("id"), rs.getString("name"), rs.getString("type"), getFoodItemDetailList(rs.getInt("id")));
                result.add(foodItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private ArrayList<FoodItemDetail> getFoodItemDetailList(int foodItemId) {
        ArrayList<FoodItemDetail> foodItemDetailList = new ArrayList<>();
        String sql = "SELECT * FROM tblfooditemdetail WHERE foodItemId = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, foodItemId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FoodItemDetail foodItemDetail = new FoodItemDetail(rs.getInt("id"), rs.getString("size"), rs.getInt("totalQuantity"), rs.getInt("price"));
                foodItemDetailList.add(foodItemDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodItemDetailList;
    }
}