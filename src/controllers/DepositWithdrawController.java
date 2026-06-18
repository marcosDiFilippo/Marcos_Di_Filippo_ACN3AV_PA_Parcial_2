package controllers;

import javax.swing.JOptionPane;

import model.BankTeller;
import services.TransactionService;

import javax.swing.JFrame;

public class DepositWithdrawController {

    private TransactionService transactionService;

    public DepositWithdrawController() {
        this.transactionService = new TransactionService();
    }

    public void processDeposit(double amount, BankTeller teller, JFrame currentView, JFrame dashboardView) {
        try {
            transactionService.processDeposit(amount, teller);
            JOptionPane.showMessageDialog(currentView, "Depósito realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            dashboardView.setVisible(true);
            currentView.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Error al procesar el depósito: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void processWithdraw(double amount, BankTeller teller, JFrame currentView, JFrame dashboardView) {
        try {
            transactionService.processWithdraw(amount, teller);
            JOptionPane.showMessageDialog(currentView, "Retiro realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            dashboardView.setVisible(true);
            currentView.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Error al procesar el retiro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
