package org.example.mapper;

import org.example.model.Customer;
import org.example.model.FoodItem;
import org.example.model.FoodItemInvoice;
import org.example.model.dto.FoodItemInvoiceDTO;

import java.util.ArrayList;

public class TableMapper {
    public static Object[][] foodItemToTable(ArrayList<FoodItem> foodItems) {
        Object[][] table = new Object[foodItems.size()][3];
        for (int i = 0; i < foodItems.size(); i++) {
            FoodItem foodItem = foodItems.get(i);
            table[i][0] = foodItem.getId();
            table[i][1] = foodItem.getName();
            table[i][2] = foodItem.getType();
        }
        return table;
    }

    public static Object[][] customerToTable(ArrayList<Customer> customers){
        Object[][] table = new Object[customers.size()][6];
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            table[i][0] = customer.getId();
            table[i][1] = customer.getFullName();
            table[i][2] = customer.getAddress();
            table[i][3] = customer.getAge();
            table[i][4] = customer.getPhoneNumber();
        }
        return table;
    }

    public static Object[][] foodItemInvoiceToTable(ArrayList<FoodItemInvoiceDTO> foodItemInvoices) {
        Object[][] table = new Object[foodItemInvoices.size()][4];
        for (int i = 0; i < foodItemInvoices.size(); i++) {
            FoodItemInvoiceDTO foodItem = foodItemInvoices.get(i);
            table[i][0] = foodItem.getId();
            table[i][1] = foodItem.getFoodItemName();
            table[i][2] = foodItem.getSize();
            table[i][3] = foodItem.getPrice();
        }
        return table;
    }
}
