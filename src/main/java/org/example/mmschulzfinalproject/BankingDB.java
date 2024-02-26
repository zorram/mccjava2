package org.example.mmschulzfinalproject;

import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.Scanner;

public class BankingDB {
    private static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:sqlite:Banking.db";
        return DriverManager.getConnection(dbURL);
    }
}