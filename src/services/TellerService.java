package services;

import javax.swing.JOptionPane;

import java.util.List;

import constants.TransactionType;

import java.sql.Connection;
import java.sql.SQLException;
import dao.BankTellerDAO;
import dao.TransactionDAO;
import model.BankTeller;
import model.DB;
import session.UserSession;

public class TellerService {

    private BankTellerDAO bankTellerDAO;

    public TellerService() {
        this.bankTellerDAO = new BankTellerDAO();
    }

    public List<BankTeller> getAllTellers() {
        return bankTellerDAO.findAll();
    }

    public void replenishCash(BankTeller teller, double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("El monto a reponer debe ser mayor a cero.");
        }
        Connection conn = DB.getConnection();
        try {
            conn.setAutoCommit(false);
            
            double newCash = teller.getAvailableCash() + amount;
            bankTellerDAO.updateAvailableCash(conn, teller.getId(), newCash);
            
            TransactionDAO transactionDAO = new TransactionDAO();
            long userId = UserSession.getInstance().getUserId();
            long typeId = transactionDAO.getTransactionTypeId(conn, TransactionType.REPOSICION_CAJERO.getDbName());
            transactionDAO.insertTransaction(conn, userId, typeId, amount, "Reposición de dinero en: " + teller.getLocation());
            
            conn.commit();
        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Error al hacer rollback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
            throw e;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) { JOptionPane.showMessageDialog(null, "Error al restaurar auto-commit: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        }
    }
}
