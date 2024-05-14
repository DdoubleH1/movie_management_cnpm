package org.example.dao;

import org.example.constant.SearchOption;
import org.example.model.Customer;

import java.sql.Array;
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
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
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


