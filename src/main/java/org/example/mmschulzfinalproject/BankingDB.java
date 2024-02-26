package org.example.mmschulzfinalproject;

import org.sqlite.SQLiteException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankingDB {
    private static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:sqlite:Banking.db";
        return DriverManager.getConnection(dbURL);
    }

    public static List<Customer> searchCustomer(Customer searchCustomer) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT Id, FirstName, LastName, PhoneNumber, Address FROM Customer ");
        sql.append("WHERE ");
        if (!searchCustomer.getFirstName().isEmpty()) {
            sql.append("FirstName = '").append(searchCustomer.getFirstName()).append("'");
        }
        System.out.println("SQL: " + sql.toString());
        try (Connection db = BankingDB.getConnection();
             Statement stmt = db.createStatement()) {
                // Let us check if it returns a true Result Set or not.
                ResultSet result = stmt.executeQuery(sql.toString());
                List<Customer> customers = new ArrayList<Customer>();
                while (result.next()) {
                    Customer customer = new Customer();
                    customer.setAccountNumber(result.getInt("Id"));
                    customer.setFirstName(result.getString("FirstName"));
                    customer.setLastName(result.getString("LastName"));
                    customer.setPhoneNumber(result.getString("PhoneNumber"));
                    customer.setAddress(result.getString("Address"));
                    customers.add(customer);
                }
            return customers;
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
        return null;
    }

    public static void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (FirstName, LastName, PhoneNumber, Address) VALUES(?, ?, ?, ?)";
        try (Connection db = BankingDB.getConnection();
             PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getAddress());
            ps.executeUpdate();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public static void updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET FirstName = ?, LastName = ?, PhoneNumber = ?, Address = ? WHERE Id = ?";
        try (Connection db = BankingDB.getConnection();
             PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getPhoneNumber());
            ps.setString(4, customer.getAddress());
            ps.setInt(5, customer.getAccountNumber());
            ps.executeUpdate();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

}