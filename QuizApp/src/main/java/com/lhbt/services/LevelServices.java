package com.lhbt.services;

import com.lhbt.pojo.Level;
import com.lhbt.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LevelServices {
    public List<Level> getLevels() throws SQLException {
        //Mo ket noi
        Connection conn = JdbcConnector.getInstance().connect();

        //Truy van
        List<Level> levels = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM level")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String note = rs.getString("note");
                Level l = new Level(id, name, note);
                levels.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách level: " + e.getMessage());
        }
        return levels;
    }
}
