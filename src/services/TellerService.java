package services;

import java.util.List;

import constants.TransactionType;

import java.sql.Connection;
import java.sql.SQLException;
import dao.BankTellerDAO;
import dao.TransactionDAO;
import model.BankTeller;
import model.DB;
import session.UserSession;
import errors.InvalidAmountException;

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
            throw new InvalidAmountException("El monto a reponer debe ser mayor a cero.");
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
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true);
        }
    }
}
