package org.example.model;

public class FoodItemDetail {
    private int id;
    private int foodItemID;
    private String size;
    private int remainingQuantity;
    private double price;

    public FoodItemDetail(int foodItemID, String size, int remainingQuantity, double price) {
        this.foodItemID = foodItemID;
        this.size = size;
        this.remainingQuantity = remainingQuantity;
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

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}