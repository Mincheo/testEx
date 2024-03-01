package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnect {
    Connection conn;
    Statement stat;
    static String dbname = "";
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "ankela12";
    public SQLConnect() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Trying connect to "+URL+dbname);
            String url = URL+dbname+"?user="+USERNAME+"&password="+PASSWORD+ "&sslmode=disable";
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();
            System.out.println("Connected to "+URL+dbname);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        stat.close();
        conn.close();

    }
}
