package controllers;

import javax.swing.JOptionPane;

import model.Transaction;
import model.BankAccount;
import session.UserSession;
import views.BalanceView;
import services.TransactionService;

import javax.swing.JFrame;
import java.util.List;

public class BalanceController {

    private TransactionService transactionService;

    public BalanceController() {
        this.transactionService = new TransactionService();
    }

    public void openBalanceView(JFrame parentView) {
        if (UserSession.getInstance() == null) {
            JOptionPane.showMessageDialog(parentView, "Sesión no válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        long userId = UserSession.getInstance().getUserId();

        try {
            BankAccount account = transactionService.getAccountDetails(userId);
            if (account == null) {
                JOptionPane.showMessageDialog(parentView, "No se encontró una cuenta para el usuario actual", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Transaction> transactions = transactionService.getTransactionHistory(userId);

            BalanceView balanceView = new BalanceView(parentView, account.getBalance(), transactions);
            balanceView.setVisible(true);
            parentView.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Error al obtener los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
