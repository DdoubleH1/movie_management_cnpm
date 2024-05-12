package org.example.dao;

import org.example.model.Invoice;

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
            ps.setInt(2, invoice.getUserId());
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                invoice.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}