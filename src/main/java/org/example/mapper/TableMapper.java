package org.example.mapper;

import org.example.model.Customer;
import org.example.model.FoodItem;
import org.example.model.FoodItemInvoice;

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

    public static Object[][] customerToTable(ArrayList<Customer> customers) {
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


    public static Object[][] foodItemInvoiceToTable(ArrayList<FoodItemInvoice> foodItemInvoices, boolean isFromExchange) {
        // get unit price from foodItemDetail
        int unitPrice = getUnitPrice(foodItemInvoices);

        Object[][] table = new Object[foodItemInvoices.size()][6];
        for (int i = 0; i < foodItemInvoices.size(); i++) {
            FoodItemInvoice foodItemInvoice = foodItemInvoices.get(i);
            table[i][0] = foodItemInvoice.getId();
            table[i][1] = foodItemInvoice.getFoodItem().getName();
            table[i][2] = foodItemInvoice.getSize();
            table[i][3] = foodItemInvoice.getQuantity();
            table[i][4] = foodItemInvoice.getUnitPrice();
            if (!isFromExchange)
                table[i][5] = foodItemInvoice.getUnitPrice() * (foodItemInvoice.getQuantity() - foodItemInvoice.getExchangeQuantity());
            else
                table[i][5] = foodItemInvoice.getUnitPrice() * foodItemInvoice.getQuantity();
        }
        return table;
    }



    private static int getUnitPrice(ArrayList<FoodItemInvoice> foodItemInvoices) {
        int unitPrice = 0;
        for (FoodItemInvoice foodItemInvoice : foodItemInvoices) {
            for (int i = 0; i < foodItemInvoice.getFoodItem().getFoodItemDetailList().size(); i++) {
                if (foodItemInvoice.getSize().equals(foodItemInvoice.getFoodItem().getFoodItemDetailList().get(i).getSize())) {
                    unitPrice = foodItemInvoice.getFoodItem().getFoodItemDetailList().get(i).getPrice();
                }
            }
        }
        return unitPrice;
    }

    public static ArrayList<FoodItemInvoice> getDisplayFoodItemInvoice(ArrayList<FoodItemInvoice> foodItemInvoices) {
        ArrayList<FoodItemInvoice> displayFoodItemInvoices = new ArrayList<>();
        for (FoodItemInvoice foodItemInvoice : foodItemInvoices) {
            if (foodItemInvoice.getQuantity() > 0) {
                for (int i = 0; i < foodItemInvoice.getQuantity(); i++) {
                    displayFoodItemInvoices.add(foodItemInvoice);
                }

            } else {
                displayFoodItemInvoices.add(foodItemInvoice);
            }
        }
        return displayFoodItemInvoices;
    }
}
