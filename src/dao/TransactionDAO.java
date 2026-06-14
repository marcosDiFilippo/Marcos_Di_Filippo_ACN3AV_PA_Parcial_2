package dao;

import model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DB;

public class TransactionDAO {
    
    public List<Transaction> findTransactionsByUserId(long userId) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        Connection conn = DB.getConnection();
        String sql = "SELECT t.id, ty.name as type, t.amount, t.description, t.created_at, t.user_id " +
                     "FROM bank_transactions t " +
                     "JOIN bank_transaction_types ty ON t.bank_transaction_type_id = ty.id " +
                     "WHERE t.user_id = ? " +
                     "ORDER BY t.created_at DESC";
                     
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setLong(1, userId);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setTypeName(rs.getString("type"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setCreatedAt(rs.getTimestamp("created_at"));
            transaction.setUserId(rs.getLong("user_id"));
            list.add(transaction);
        }
        
        return list;
    }

    public List<Transaction> findAllTransactions() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        Connection conn = DB.getConnection();
        String sql = "SELECT t.id, ty.name as type, t.amount, t.description, t.created_at, t.user_id " +
                     "FROM bank_transactions t " +
                     "JOIN bank_transaction_types ty ON t.bank_transaction_type_id = ty.id " +
                     "ORDER BY t.created_at DESC";
                     
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setTypeName(rs.getString("type"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setCreatedAt(rs.getTimestamp("created_at"));
            transaction.setUserId(rs.getLong("user_id"));
            list.add(transaction);
        }
        
        return list;
    }

    public List<Transaction> findTellerTransactions() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        Connection conn = DB.getConnection();
        String sql = "SELECT t.id, ty.name as type, t.amount, t.description, t.created_at, t.user_id " +
                     "FROM bank_transactions t " +
                     "JOIN bank_transaction_types ty ON t.bank_transaction_type_id = ty.id " +
                     "WHERE ty.name IN ('Deposito', 'Retiro', 'Reposicion Cajero') " +
                     "ORDER BY t.created_at DESC";
                     
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setTypeName(rs.getString("type"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setCreatedAt(rs.getTimestamp("created_at"));
            transaction.setUserId(rs.getLong("user_id"));
            list.add(transaction);
        }
        
        return list;
    }

    public List<Transaction> findTransferTransactions() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        Connection conn = DB.getConnection();
        String sql = "SELECT t.id, ty.name as type, t.amount, t.description, t.created_at, t.user_id " +
                     "FROM bank_transactions t " +
                     "JOIN bank_transaction_types ty ON t.bank_transaction_type_id = ty.id " +
                     "WHERE ty.name = 'Transferencia' " +
                     "ORDER BY t.created_at DESC";
                     
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setTypeName(rs.getString("type"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setCreatedAt(rs.getTimestamp("created_at"));
            transaction.setUserId(rs.getLong("user_id"));
            list.add(transaction);
        }
        
        return list;
    }

    public long getTransactionTypeId(Connection conn, String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT id FROM bank_transaction_types WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getLong("id");
        }
        return -1;
    }

    public long insertTransaction(Connection conn, long userId, long transactionTypeId, double amount, String description) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO bank_transactions (user_id, bank_transaction_type_id, amount, description) VALUES (?, ?, ?, ?)",
            java.sql.Statement.RETURN_GENERATED_KEYS
        );
        stmt.setLong(1, userId);
        stmt.setLong(2, transactionTypeId);
        stmt.setDouble(3, amount);
        stmt.setString(4, description);
        stmt.executeUpdate();
        
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return -1;
    }

    public void insertTransfer(Connection conn, long bankTransactionId, long sourceAccountId, long destinationAccountId) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO bank_transfers (bank_transaction_id, source_account_id, destination_account_id) VALUES (?, ?, ?)"
        );
        stmt.setLong(1, bankTransactionId);
        stmt.setLong(2, sourceAccountId);
        stmt.setLong(3, destinationAccountId);
        stmt.executeUpdate();
    }
}
