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

    public ArrayList<FoodItem> searchFoodItem(String foodName) {
        ArrayList<FoodItem> result = new ArrayList<>();
        String sqlFoodItem = "SELECT * FROM tblfooditem WHERE name LIKE ?";

        try {
            PreparedStatement psFoodItem = con.prepareStatement(sqlFoodItem);
            psFoodItem.setString(1, "%" + foodName + "%");
            ResultSet rsFoodItem = psFoodItem.executeQuery();
            while (rsFoodItem.next()) {
                int foodItemId = rsFoodItem.getInt("id");
                FoodItem foodItem = new FoodItem(foodItemId, rsFoodItem.getString("name"), rsFoodItem.getString("type"), new ArrayList<>());

                ArrayList<FoodItemDetail> foodItemDetailList = new ArrayList<>();
                String sqlFoodItemDetail = "SELECT * FROM tblfooditemdetail WHERE foodItemId = ?";
                PreparedStatement psFoodItemDetail = con.prepareStatement(sqlFoodItemDetail);
                psFoodItemDetail.setInt(1, foodItemId);
                ResultSet rsFoodItemDetail = psFoodItemDetail.executeQuery();
                while (rsFoodItemDetail.next()) {
                    FoodItemDetail foodItemDetail = new FoodItemDetail(rsFoodItemDetail.getInt("id"), rsFoodItemDetail.getString("size"), rsFoodItemDetail.getInt("totalQuantity"), rsFoodItemDetail.getInt("price"));
                    foodItemDetailList.add(foodItemDetail);
                }

                foodItem.setFoodItemDetailList(foodItemDetailList);
                result.add(foodItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
