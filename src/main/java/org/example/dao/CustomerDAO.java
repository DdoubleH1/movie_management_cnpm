package org.example.dao;

import org.example.constant.SearchOption;
import org.example.model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAO extends DAO {
    public CustomerDAO() {
        super();
    }

    public Customer addCustomer(Customer customer) {
        String query = "INSERT INTO tblcustomer (fullName, address, age, phoneNumber) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getAddress());
            ps.setInt(3, customer.getAge());
            ps.setString(4, customer.getPhoneNumber());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public ArrayList<Customer> searchCustomer(String keyword, SearchOption searchOption) {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM tblcustomer WHERE " + searchOption.getValue() + " LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("id"), rs.getString("fullName"), rs.getString("address"), rs.getInt("age"), rs.getString("phoneNumber"));
                customer.setMembershipPoint(getMembershipPoint(customer));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public int getMembershipPoint(Customer customer) {
        int membershipPoint = 0;
        String queryTotalPurchase = "SELECT SUM(tblfooditeminvoice.quantity * tblfooditemdetail.price) AS membership_point " +
                "FROM tblinvoice " +
                "JOIN tblfooditeminvoice ON tblinvoice.id = tblfooditeminvoice.invoiceID " +
                "JOIN tblfooditemdetail ON tblfooditeminvoice.foodItemID = tblfooditemdetail.foodItemID AND tblfooditeminvoice.size = tblfooditemdetail.size " +
                "WHERE tblinvoice.customerID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(queryTotalPurchase);
            ps.setInt(1, customer.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                membershipPoint = rs.getInt("membership_point");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String queryExchange = "SELECT SUM(tblfooditeminvoice.exchangeQuantity * tblfooditemdetail.price) AS membership_point " +
                "FROM tblinvoice " +
                "JOIN tblfooditeminvoice ON tblinvoice.id = tblfooditeminvoice.invoiceID " +
                "JOIN tblfooditemdetail ON tblfooditeminvoice.foodItemID = tblfooditemdetail.foodItemID AND tblfooditeminvoice.size = tblfooditemdetail.size " +
                "WHERE tblinvoice.customerID = ? AND tblfooditeminvoice.exchangeQuantity > 0";
        try {
            PreparedStatement ps = con.prepareStatement(queryExchange);
            ps.setInt(1, customer.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                membershipPoint -= rs.getInt("membership_point");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(membershipPoint);
        return (int) (membershipPoint * 0.1);
    }

    public Customer getCustomerById(int customerId) {
        Customer customer = null;
        String query = "SELECT * FROM tblcustomer WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(rs.getInt("id"), rs.getString("fullName"), rs.getString("address"), rs.getInt("age"), rs.getString("phoneNumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }
}


