package org.example.model.dto;

public class FoodItemInvoiceDTO {
    private int id;
    private String size;
    private String foodItemName; // Replace the foodItemId with foodItemName
    private int price;

    public FoodItemInvoiceDTO() {
    }

    public FoodItemInvoiceDTO(int id, String size, String foodItemName, int price) {
        this.id = id;
        this.size = size;
        this.foodItemName = foodItemName;
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

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FoodItemInvoiceDTO{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", foodItemName='" + foodItemName + '\'' +
                ", price=" + price +
                '}';
    }
}
