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
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM level");

        List<Level> levels = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");

            Level l = new Level(id, name);
            levels.add(l);
        }

        conn.close();
        return levels;
    }
}
