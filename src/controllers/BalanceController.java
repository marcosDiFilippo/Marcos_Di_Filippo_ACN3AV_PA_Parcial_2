package controllers;

import javax.swing.JOptionPane;

import model.Transaction;
import model.BankAccount;
import session.UserSession;
import views.BalanceView;
import views.DashboardUser;
import services.TransactionService;

import javax.swing.JFrame;
import java.util.List;

public class BalanceController {

    private TransactionService transactionService;

    public BalanceController() {
        this.transactionService = new TransactionService();
    }

    public void openBalanceView(JFrame currentView) {
        if (UserSession.getInstance() == null) {
            JOptionPane.showMessageDialog(currentView, "Sesión no válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        long userId = UserSession.getInstance().getUserId();

        try {
            BankAccount account = transactionService.getAccountDetails(userId);
            if (account == null) {
                JOptionPane.showMessageDialog(currentView, "No se encontró una cuenta para el usuario actual", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Transaction> transactions = transactionService.getTransactionHistory(userId);

            BalanceView balanceView = new BalanceView(this, account.getBalance(), transactions);
            balanceView.setVisible(true);
            currentView.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void goBack(JFrame currentView) {
        currentView.dispose();
        new DashboardUser().setVisible(true);
    }
}
