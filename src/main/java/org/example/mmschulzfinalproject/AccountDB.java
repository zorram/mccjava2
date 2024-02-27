package org.example.mmschulzfinalproject;

import org.sqlite.SQLiteException;

import java.sql.*;

public class AccountDB {
    private static Connection getConnection() throws SQLException {
        String dbURL = "jdbc:sqlite:Banking.db";
        return DriverManager.getConnection(dbURL);
    }
    public static Account addAccount(Account account) {
        String sql = "INSERT INTO Account (Balance, InterestRate) VALUES(?, ?)";
        try (Connection db = AccountDB.getConnection();
             PreparedStatement ps = db.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBigDecimal(1, account.getBalance());
            ps.setBigDecimal(2, account.getInterestRate());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }
            return account;
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
        return null;
    }
    public static void updateBalance(Account account) {
        String sql = "UPDATE Account SET Balance = ? WHERE Id = ?";
        try (Connection db = AccountDB.getConnection();
             PreparedStatement ps = db.prepareStatement(sql)) {
            ps.setBigDecimal(1, account.getBalance());
            ps.setInt(2, account.getId());
            ps.executeUpdate();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
   }

}
