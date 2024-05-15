package org.example.model;

import java.util.ArrayList;

public class Invoice {
    private int id;
    private String payDate;
    private User user;
    private Customer customer;
    private ArrayList<FoodItemInvoice> foodItemInvoices;
    private ArrayList<Ticket> tickets;

    public Invoice() {
    }

    public Invoice(int id, String payDate, User user, Customer customer, ArrayList<FoodItemInvoice> foodItemInvoices, ArrayList<Ticket> tickets) {
        this.id = id;
        this.payDate = payDate;
        this.user = user;
        this.customer = customer;
        this.foodItemInvoices = foodItemInvoices;
        this.tickets = tickets;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<FoodItemInvoice> getFoodItemInvoices() {
        return foodItemInvoices;
    }

    public void setFoodItemInvoices(ArrayList<FoodItemInvoice> foodItemInvoices) {
        this.foodItemInvoices = foodItemInvoices;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
