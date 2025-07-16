package com.lhbt.services;

import com.lhbt.pojo.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServices extends BaseServices {

    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM category");
    }

    @Override
    public List getResults(ResultSet rs) throws SQLException {
        List<Category> cates = new ArrayList<>();
        while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category c = new Category(id, name);
                cates.add(c);
        }
        return cates;
    }

    //    public List<Category> getCates() throws SQLException {
//        //Mo ket noi
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        //Truy van
//        List<Category> cates = new ArrayList<>();
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM category")) {
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                Category c = new Category(id, name);
//                cates.add(c);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi lấy danh sách category: " + e.getMessage());
//        }
//
//        return cates;
//    }
}
