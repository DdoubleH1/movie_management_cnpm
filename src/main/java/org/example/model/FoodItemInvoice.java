package org.example.model;

public class FoodItemInvoice {
    private int id;
    private String size;
    private int quantity;
    private int exchangeQuantity;
    private FoodItem foodItem;

    public FoodItemInvoice() {
    }

    public FoodItemInvoice(String size, int quantity, int exchangeQuantity, FoodItem foodItem) {
        this.size = size;
        this.quantity = quantity;
        this.exchangeQuantity = exchangeQuantity;
        this.foodItem = foodItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getExchangeQuantity() {
        return exchangeQuantity;
    }

    public void setExchangeQuantity(int exchangeQuantity) {
        this.exchangeQuantity = exchangeQuantity;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}
