package org.example.mmschulzfinalproject;

import org.sqlite.SQLiteException;

import java.math.BigDecimal;
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
        sql.append("SELECT Customer.Id, Customer.FirstName, Customer.LastName, Customer.PhoneNumber, Customer.Address, ");
        sql.append("Account.Id as AccountId, Account.Balance, Account.InterestRate ");
        sql.append("FROM Customer ");
        sql.append("INNER JOIN Account ON Account.Id = Customer.AccountId ");
        sql.append("WHERE ");
        if (!searchCustomer.getFirstName().isEmpty()) {
            sql.append("Customer.FirstName = '").append(searchCustomer.getFirstName()).append("'");
        }
        System.out.println("SQL: " + sql.toString());
        try (Connection db = BankingDB.getConnection();
             Statement stmt = db.createStatement()) {
                ResultSet result = stmt.executeQuery(sql.toString());
                List<Customer> customers = new ArrayList<Customer>();

                System.out.println("result size: " + result.getFetchSize());
                while (result.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(result.getInt("Id"));
                    customer.setFirstName(result.getString("FirstName"));
                    customer.setLastName(result.getString("LastName"));
                    customer.setPhoneNumber(result.getString("PhoneNumber"));
                    customer.setAddress(result.getString("Address"));

                    Account account = new Account();
                    int accountId = result.getInt("AccountId");
                    if (accountId > 0) {
                        account.setId(accountId);
                    }
                    BigDecimal balance = result.getBigDecimal("Balance");
                    if (balance != null) {
                        account.setBalance(balance);
                    }
                    BigDecimal interestRate = result.getBigDecimal("InterestRate");
                    if (interestRate != null) {
                        account.setInterestRate(interestRate);
                    }

                    customer.setAccount(account);
                    customers.add(customer);
                }
            return customers;
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
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
            ps.setInt(5, customer.getCustomerId());
            ps.executeUpdate();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

}