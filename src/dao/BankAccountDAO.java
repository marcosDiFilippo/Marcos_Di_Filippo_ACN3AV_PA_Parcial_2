package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.BankAccount;
import model.DB;

public class BankAccountDAO {
    public BankAccount findByUserId(long userId) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bank_accounts WHERE user_id = ?");
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            BankAccount bankAccount = new BankAccount();
            bankAccount.setId(rs.getLong("id"));
            bankAccount.setUserId(rs.getLong("user_id"));
            bankAccount.setBalance(rs.getDouble("balance"));
            bankAccount.setAccountNumber(rs.getInt("account_number"));
            bankAccount.setAlias(rs.getString("alias"));
            
            return bankAccount;
        } catch (SQLException e) {
            throw e;
        }
    }

    public BankAccount findByAccountNumberOrAlias(String input) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bank_accounts WHERE account_number = ? OR alias = ?");
            try {
                int accNumber = Integer.parseInt(input);
                stmt.setInt(1, accNumber);
            } catch (NumberFormatException e) {
                stmt.setInt(1, -1);
            }
            stmt.setString(2, input);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            BankAccount bankAccount = new BankAccount();
            bankAccount.setId(rs.getLong("id"));
            bankAccount.setUserId(rs.getLong("user_id"));
            bankAccount.setBalance(rs.getDouble("balance"));
            bankAccount.setAccountNumber(rs.getInt("account_number"));
            bankAccount.setAlias(rs.getString("alias"));
            
            return bankAccount;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void updateBalance(Connection conn, long accountId, double newBalance) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE bank_accounts SET balance = ? WHERE id = ?");
        stmt.setDouble(1, newBalance);
        stmt.setLong(2, accountId);
        stmt.executeUpdate();
    }
}
