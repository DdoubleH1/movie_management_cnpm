package org.example.dao;

import org.example.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DAO {

    public UserDAO() {
        super();
    }

    public User checkLogin(User user) {

        String sql = "SELECT * FROM tblUser WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("fullName"));
                user.setPosition(rs.getString("position"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}