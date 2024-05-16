package org.example.mapper;

import org.example.model.dto.FoodItemInvoiceDTO;

import java.util.ArrayList;

public class FoodItemInvoiceMapper {
    public static ArrayList<FoodItemInvoiceDTO> foodItemInvoiceToDTO(ArrayList<FoodItemInvoiceDTO> foodItemInvoices) {
        ArrayList<FoodItemInvoiceDTO> foodItemInvoiceDTOS = new ArrayList<>();
        for (FoodItemInvoiceDTO foodItemInvoice : foodItemInvoices) {
            FoodItemInvoiceDTO foodItemInvoiceDTO = new FoodItemInvoiceDTO();
            foodItemInvoiceDTO.setId(foodItemInvoice.getId());
            foodItemInvoiceDTO.setSize(foodItemInvoice.getSize());
            foodItemInvoiceDTO.setFoodItemName(foodItemInvoice.getFoodItemName());
            foodItemInvoiceDTO.setPrice(foodItemInvoice.getPrice());
            foodItemInvoiceDTOS.add(foodItemInvoiceDTO);
        }
        return foodItemInvoiceDTOS;
    }
}
