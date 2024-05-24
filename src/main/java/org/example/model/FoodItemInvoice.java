package org.example.model;

import java.util.Objects;

public class FoodItemInvoice {
    private int id;
    private String size;
    private int quantity;
    private int exchangeQuantity;
    private FoodItem foodItem;
    private int unitPrice;

    public FoodItemInvoice() {
    }

    public FoodItemInvoice(String size, int quantity, int exchangeQuantity, FoodItem foodItem, int unitPrice) {
        this.size = size;
        this.quantity = quantity;
        this.exchangeQuantity = exchangeQuantity;
        this.foodItem = foodItem;
        this.unitPrice = unitPrice;
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "FoodItemInvoice{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", exchangeQuantity=" + exchangeQuantity +
                ", foodItem=" + foodItem +
                ", unitPrice=" + unitPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodItemInvoice that = (FoodItemInvoice) o;
        return Objects.equals(foodItem, that.foodItem);
    }
}
