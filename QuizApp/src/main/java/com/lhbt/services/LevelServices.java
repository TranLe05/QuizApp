package com.lhbt.services;

import com.lhbt.pojo.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LevelServices extends BaseServices {
    @Override
    public PreparedStatement getStm(Connection conn) throws SQLException {
        return conn.prepareCall("SELECT * FROM level");
    }

    @Override
    public List getResults(ResultSet rs) throws SQLException {
        List<Level> levels = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String note = rs.getString("note");
            Level l = new Level(id, name, note);
            levels.add(l);
        }
        return levels;
    }

//    public List<Level> getLevels() throws SQLException {
//        //Mo ket noi
//        Connection conn = JdbcConnector.getInstance().connect();
//
//        //Truy van
//        List<Level> levels = new ArrayList<>();
//        try (Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM level")) {
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                String note = rs.getString("note");
//                Level l = new Level(id, name, note);
//                levels.add(l);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi lấy danh sách level: " + e.getMessage());
//        }
//        return levels;
//    }
}
