package org.example.model;

import java.util.ArrayList;

public class FoodItem {
    private int id;
    private String name;
    private String type;
    private ArrayList<FoodItemDetail> foodItemDetailList;

    public FoodItem() {
    }

    public FoodItem(int id, String name, String type, ArrayList<FoodItemDetail> foodItemDetailList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.foodItemDetailList = foodItemDetailList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<FoodItemDetail> getFoodItemDetailList() {
        return foodItemDetailList;
    }

    public void setFoodItemDetailList(ArrayList<FoodItemDetail> foodItemDetailList) {
        this.foodItemDetailList = foodItemDetailList;
    }
}
