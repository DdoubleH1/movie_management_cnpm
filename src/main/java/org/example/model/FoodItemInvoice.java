package org.example.model;

public class FoodItemInvoice {
    private int id;
    private String size;
    private int quantity;
    private boolean isExchangeByMembership;
    private FoodItem foodItem;

    public FoodItemInvoice() {
    }

    public FoodItemInvoice(String size, int quantity, boolean isExchangeByMembership, FoodItem foodItem) {
        this.size = size;
        this.quantity = quantity;
        this.isExchangeByMembership = isExchangeByMembership;
        this.foodItem = foodItem;
    }

    public FoodItemInvoice(int id, String size, int quantity, boolean isExchangeByMembership, FoodItem foodItem) {
        this.id = id;
        this.size = size;
        this.quantity = quantity;
        this.isExchangeByMembership = isExchangeByMembership;
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

    public boolean isExchangeByMembership() {
        return isExchangeByMembership;
    }

    public void setExchangeByMembership(boolean exchangeByMembership) {
        isExchangeByMembership = exchangeByMembership;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}
