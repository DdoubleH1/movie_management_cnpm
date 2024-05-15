package org.example.model;

public class FoodItemDetail {
    private int id;
    private String size;
    private int totalQuantity;
    private int price;

    public FoodItemDetail() {
    }

    public FoodItemDetail(int id, String size, int totalQuantity, int price) {
        this.id = id;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}