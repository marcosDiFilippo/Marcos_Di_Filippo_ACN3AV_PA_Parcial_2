package dao;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.BankTeller;
import model.DB;

public class BankTellerDAO {

    public List<BankTeller> findAll() {
        List<BankTeller> tellers = new ArrayList<>();
        String query = "SELECT * FROM bank_tellers";
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BankTeller teller = new BankTeller();
                teller.setId(rs.getLong("id"));
                teller.setLocation(rs.getString("location"));
                teller.setAvailableCash(rs.getDouble("available_cash"));
                tellers.add(teller);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
        return tellers;
    }

    public void updateAvailableCash(Connection conn, long tellerId, double newCash) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE bank_tellers SET available_cash = ? WHERE id = ?");
        stmt.setDouble(1, newCash);
        stmt.setLong(2, tellerId);
        stmt.executeUpdate();
    }
}
