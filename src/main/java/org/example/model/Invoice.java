package org.example.model;

public class Invoice {
    private int id;
    private String payDate;
    private int userId;
    private int customerId;

    public Invoice() {
    }

    public Invoice(String payDate, int userId, int customerId) {
        this.payDate = payDate;
        this.userId = userId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
