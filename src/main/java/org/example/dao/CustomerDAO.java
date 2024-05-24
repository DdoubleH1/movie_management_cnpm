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

    public boolean addCustomer(Customer customer) {
        boolean result = true;
        //check if customer already exists
        String query = "SELECT * FROM tblcustomer WHERE fullName = ? AND address = ? AND age = ? AND phoneNumber = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getAddress());
            ps.setInt(3, customer.getAge());
            ps.setString(4, customer.getPhoneNumber());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = false;
            }
            else {
                String insertQuery = "INSERT INTO tblcustomer (fullName, address, age, phoneNumber) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                psInsert.setString(1, customer.getFullName());
                psInsert.setString(2, customer.getAddress());
                psInsert.setInt(3, customer.getAge());
                psInsert.setString(4, customer.getPhoneNumber());
                psInsert.executeUpdate();
                ResultSet generatedKeys = psInsert.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

                // Calculate membership points
                String queryTotal = "SELECT SUM((tblfooditeminvoice.quantity - tblfooditeminvoice.exchangeQuantity) * tblfooditemdetail.price) AS total " +
                        "FROM tblinvoice " +
                        "JOIN tblfooditeminvoice ON tblinvoice.id = tblfooditeminvoice.invoiceID " +
                        "JOIN tblfooditemdetail ON tblfooditeminvoice.foodItemID = tblfooditemdetail.foodItemID AND tblfooditeminvoice.size = tblfooditemdetail.size " +
                        "WHERE tblinvoice.customerID = ?";
                PreparedStatement psTotal = con.prepareStatement(queryTotal);
                psTotal.setInt(1, customer.getId());
                ResultSet rsTotal = psTotal.executeQuery();
                rsTotal.next();
                int total = rsTotal.getInt("total");

                String queryExchange = "SELECT SUM(tblfooditeminvoice.exchangeQuantity * tblfooditemdetail.price) AS total " +
                        "FROM tblinvoice " +
                        "JOIN tblfooditeminvoice ON tblinvoice.id = tblfooditeminvoice.invoiceID " +
                        "JOIN tblfooditemdetail ON tblfooditeminvoice.foodItemID = tblfooditemdetail.foodItemID AND tblfooditeminvoice.size = tblfooditemdetail.size " +
                        "WHERE tblinvoice.customerID = ? AND tblfooditeminvoice.exchangeQuantity > 0";
                PreparedStatement psExchange = con.prepareStatement(queryExchange);
                psExchange.setInt(1, customer.getId());
                ResultSet rsExchange = psExchange.executeQuery();
                rsExchange.next();
                int exchange = rsExchange.getInt("total");
                int membershipPoint = (int) ((total - exchange) * 0.1);
                customer.setMembershipPoint(membershipPoint);
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

}


