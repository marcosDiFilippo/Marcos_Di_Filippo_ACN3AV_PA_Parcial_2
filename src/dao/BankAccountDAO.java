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
}
