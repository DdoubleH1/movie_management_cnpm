package org.example.model;

public class FoodItemInvoice {
    private int id;
    private String size;
    private int quantity;
    private int foodItemId;
    private int invoiceId;

    public FoodItemInvoice() {
    }

    public FoodItemInvoice(String size, int quantity, int foodItemId, int invoiceId) {
        this.size = size;
        this.quantity = quantity;
        this.foodItemId = foodItemId;
        this.invoiceId = invoiceId;
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

    public int getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }
}
