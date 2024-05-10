package org.example.mapper;

import org.example.model.FoodItem;

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
}
