package services;

import dao.BankAccountDAO;
import dao.BankTellerDAO;
import dao.TransactionDAO;
import model.Transaction;
import model.BankAccount;
import model.BankTeller;
import model.DB;
import session.UserSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import constants.TransactionType;

public class TransactionService {

    private BankAccountDAO bankAccountDAO;
    private BankTellerDAO bankTellerDAO;
    private TransactionDAO transactionDAO;

    public TransactionService() {
        this.bankAccountDAO = new BankAccountDAO();
        this.bankTellerDAO = new BankTellerDAO();
        this.transactionDAO = new TransactionDAO();
    }

    public void processDeposit(double amount, BankTeller teller) throws Exception {
        if (amount <= 0) {
            throw new Exception("El monto debe ser mayor a cero.");
        }

        Connection conn = DB.getConnection();
        try {
            conn.setAutoCommit(false);
            
            long userId = UserSession.getInstance().getUserId();
            BankAccount account = bankAccountDAO.findByUserId(userId);
            
            if (account == null) {
                throw new Exception("Cuenta no encontrada para el usuario actual.");
            }

            double newBalance = account.getBalance() + amount;
            bankAccountDAO.updateBalance(conn, account.getId(), newBalance);
            
            double newCash = teller.getAvailableCash() + amount;
            bankTellerDAO.updateAvailableCash(conn, teller.getId(), newCash);
            
            long typeId = transactionDAO.getTransactionTypeId(conn, TransactionType.DEPOSITO.getDbName());
            transactionDAO.insertTransaction(conn, userId, typeId, amount, "Depósito en cajero: " + teller.getLocation());

            conn.commit();
        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            throw e;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public void processWithdraw(double amount, BankTeller teller) throws Exception {
        if (amount <= 0) {
            throw new Exception("El monto debe ser mayor a cero.");
        }

        Connection conn = DB.getConnection();
        try {
            conn.setAutoCommit(false);
            
            long userId = UserSession.getInstance().getUserId();
            BankAccount account = bankAccountDAO.findByUserId(userId);
            
            if (account == null) {
                throw new Exception("Cuenta no encontrada para el usuario actual.");
            }

            if (account.getBalance() < amount) {
                throw new Exception("Saldo insuficiente.");
            }

            if (teller.getAvailableCash() < amount) {
                throw new Exception("El cajero no tiene suficiente dinero disponible.");
            }

            double newBalance = account.getBalance() - amount;
            bankAccountDAO.updateBalance(conn, account.getId(), newBalance);
            
            double newCash = teller.getAvailableCash() - amount;
            bankTellerDAO.updateAvailableCash(conn, teller.getId(), newCash);
            
            long typeId = transactionDAO.getTransactionTypeId(conn, TransactionType.RETIRO.getDbName());
            transactionDAO.insertTransaction(conn, userId, typeId, amount, "Retiro en cajero: " + teller.getLocation());

            conn.commit();
        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            throw e;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public void processTransfer(double amount, String destinationInput) throws Exception {
        if (amount <= 0) {
            throw new Exception("El monto a transferir debe ser mayor a cero.");
        }

        Connection conn = DB.getConnection();
        try {
            conn.setAutoCommit(false);
            
            long userId = UserSession.getInstance().getUserId();
            BankAccount sourceAccount = bankAccountDAO.findByUserId(userId);
            
            if (sourceAccount == null) {
                throw new Exception("No se encontró su cuenta.");
            }

            if (sourceAccount.getBalance() < amount) {
                throw new Exception("Saldo insuficiente para realizar la transferencia.");
            }

            BankAccount destinationAccount = bankAccountDAO.findByAccountNumberOrAlias(destinationInput);

            if (destinationAccount == null) {
                throw new Exception("La cuenta destino no existe.");
            }

            if (sourceAccount.getId() == destinationAccount.getId()) {
                throw new Exception("No puede transferir dinero a su misma cuenta.");
            }

            double newSourceBalance = sourceAccount.getBalance() - amount;
            bankAccountDAO.updateBalance(conn, sourceAccount.getId(), newSourceBalance);
            
            double newDestBalance = destinationAccount.getBalance() + amount;
            bankAccountDAO.updateBalance(conn, destinationAccount.getId(), newDestBalance);
            
            long typeId = transactionDAO.getTransactionTypeId(conn, TransactionType.TRANSFERENCIA.getDbName());
            long transactionId = transactionDAO.insertTransaction(conn, userId, typeId, amount, "Transferencia a " + destinationAccount.getAlias());
            
            transactionDAO.insertTransfer(conn, transactionId, sourceAccount.getId(), destinationAccount.getId());

            conn.commit();
        } catch (Exception e) {
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            throw e;
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public BankAccount getAccountDetails(long userId) throws SQLException {
        return bankAccountDAO.findByUserId(userId);
    }

    public List<Transaction> getTransactionHistory(long userId) throws SQLException {
        return transactionDAO.findTransactionsByUserId(userId);
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        return transactionDAO.findAllTransactions();
    }

    public List<Transaction> getTellerTransactions() throws SQLException {
        return transactionDAO.findTellerTransactions();
    }

    public List<Transaction> getTransferTransactions() throws SQLException {
        return transactionDAO.findTransferTransactions();
    }
}
