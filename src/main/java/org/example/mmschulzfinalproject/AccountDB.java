package org.example.mmschulzfinalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccountDB {
    private static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:sqlite:Banking.db";
        return DriverManager.getConnection(dbURL);
    }


}
