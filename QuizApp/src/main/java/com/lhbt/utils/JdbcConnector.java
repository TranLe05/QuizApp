package com.lhbt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnector {
    private static JdbcConnector instance;
    private final Connection conn;

    //Khong can Class.forName de nap driver vi Java23 ho tro tu dong tai driver => tranh loi ClassNotFoundException
    private JdbcConnector() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/quizdb";
        String user = "root";
        String password = "Baotran1805@";
        this.conn = DriverManager.getConnection(url, user, password);
    }

    public static JdbcConnector getInstance() throws SQLException {
        if (instance == null)
            instance = new JdbcConnector();
        return instance;
    }

    public Connection connect() {
        return this.conn;
    }

    public void close() throws SQLException {
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
        }
    }
}