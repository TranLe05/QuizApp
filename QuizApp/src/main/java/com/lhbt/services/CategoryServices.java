package com.lhbt.services;

import com.lhbt.pojo.Category;
import com.lhbt.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryServices {
    public List<Category> getCates() throws SQLException {
        //Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();

        //Truy van
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM category");

        List<Category> cates = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Category c = new Category(id, name);
            cates.add(c);
        }

        return cates;
    }
}
