package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DB;
import model.BankEmployee;

public class BankEmployeeDAO {
    public BankEmployee findById(long userId) throws SQLException {
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bank_employees WHERE user_id = ?");
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                return null;
            }

            BankEmployee bankEmployee = new BankEmployee();
            bankEmployee.setId(rs.getLong("id"));
            bankEmployee.setUserId(rs.getLong("user_id"));
            
            return bankEmployee;
        } catch (SQLException e) {
            throw e;
        }
    }
}
