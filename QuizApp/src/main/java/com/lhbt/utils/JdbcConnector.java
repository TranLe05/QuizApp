package com.lhbt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcConnector {
    private static JdbcConnector instance;
    private final Connection conn;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JdbcConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Khong can Class.forName de nap driver vi Java23 ho tro tu dong tai driver => tranh loi ClassNotFoundException
    private JdbcConnector() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/quizdb";
        String user = "root";
        String password = "root";
        this.conn = DriverManager.getConnection(url, user, password);
    }

    public static JdbcConnector getInstance() throws SQLException {
        if (instance == null)
            instance = new JdbcConnector();
        return instance;
    }

    public synchronized Connection connect() {
        return this.conn;
    }

    public synchronized void close() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
        }
    }
}
