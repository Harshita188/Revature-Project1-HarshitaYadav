package com.revworkforce.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/rev_workforce?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Hysqlpassword1234"; // change if needed

    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get the connection
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
}