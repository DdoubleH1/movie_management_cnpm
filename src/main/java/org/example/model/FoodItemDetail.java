package org.example.model;

public class FoodItemDetail {
    private int id;
    private int foodItemID;
    private String size;
    private int totalQuantity;
    private double price;

    public FoodItemDetail(int foodItemID, String size, int totalQuantity, double price) {
        this.foodItemID = foodItemID;
        this.size = size;
        this.totalQuantity = totalQuantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(int foodItemID) {
        this.foodItemID = foodItemID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int gettotalQuantity() {
        return totalQuantity;
    }

    public void settotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}