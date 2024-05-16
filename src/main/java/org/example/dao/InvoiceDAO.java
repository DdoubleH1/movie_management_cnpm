package org.example.dao;

import org.example.model.FoodItemInvoice;
import org.example.model.Invoice;
import org.example.model.dto.FoodItemInvoiceDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InvoiceDAO extends DAO {

    public InvoiceDAO() {
        super();
    }

    public void addInvoice(Invoice invoice) {
        String query = "INSERT INTO tblinvoice (payDate, userID) VALUES (?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, invoice.getPayDate());
            ps.setInt(2, invoice.getUser().getId());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Invoice updateInvoice(Invoice invoice) {
        String query = "UPDATE tblinvoice SET customerID = ? WHERE payDate = ? AND userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, invoice.getCustomer().getId());
            ps.setString(2, invoice.getPayDate());
            ps.setInt(3, invoice.getUser().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public FoodItemInvoiceDTO getFoodItemInvoicesDTO(FoodItemInvoice foodItemInvoice) {

        FoodItemInvoiceDTO foodItemInvoiceDTO = new FoodItemInvoiceDTO();
        String query = "SELECT tblfooditemdetail.price from tblfooditemdetail " +
                "JOIN tblfooditeminvoice ON tblfooditemdetail.foodItemID = tblfooditeminvoice.foodItemID AND tblfooditemdetail.size = tblfooditeminvoice.size ";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                foodItemInvoiceDTO.setId(foodItemInvoice.getId());
                foodItemInvoiceDTO.setSize(foodItemInvoice.getSize());
                foodItemInvoiceDTO.setFoodItemName(foodItemInvoice.getFoodItem().getName());
                foodItemInvoiceDTO.setPrice(rs.getInt("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return foodItemInvoiceDTO;


    }

    public int getMembershipPoint(Invoice invoice) {
        int membershipPoint = 0;
        String queryTotalPurchase = "SELECT SUM(tblfooditeminvoice.quantity * tblfooditemdetail.price) AS membership_point " +
                "FROM tblinvoice " +
                "JOIN tblfooditeminvoice ON tblinvoice.id = tblfooditeminvoice.invoiceID " +
                "JOIN tblfooditemdetail ON tblfooditeminvoice.foodItemID = tblfooditemdetail.foodItemID AND tblfooditeminvoice.size = tblfooditemdetail.size " +
                "WHERE tblinvoice.customerID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(queryTotalPurchase);
            ps.setInt(1, invoice.getCustomer().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                membershipPoint = rs.getInt("membership_point");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String queryExchange = "SELECT SUM(tblfooditeminvoice.quantity * tblfooditemdetail.price) AS membership_point " +
                "FROM tblinvoice " +
                "JOIN tblfooditeminvoice ON tblinvoice.id = tblfooditeminvoice.invoiceID " +
                "JOIN tblfooditemdetail ON tblfooditeminvoice.foodItemID = tblfooditemdetail.foodItemID AND tblfooditeminvoice.size = tblfooditemdetail.size " +
                "WHERE tblinvoice.customerID = ? AND tblfooditeminvoice.isExchangeByMembership = 1";
        try {
            PreparedStatement ps = con.prepareStatement(queryExchange);
            ps.setInt(1, invoice.getCustomer().getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                membershipPoint -= rs.getInt("membership_point");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membershipPoint;
    }
}