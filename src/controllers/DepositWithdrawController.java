package controllers;

import javax.swing.JOptionPane;

import model.BankTeller;
import services.TransactionService;
import errors.AccountNotFoundException;
import errors.InsufficientFundsException;
import errors.InvalidAmountException;
import errors.TellerOutOfCashException;

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
            
        } catch (InvalidAmountException | AccountNotFoundException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Depósito", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void processWithdraw(double amount, BankTeller teller, JFrame currentView, JFrame dashboardView) {
        try {
            transactionService.processWithdraw(amount, teller);
            JOptionPane.showMessageDialog(currentView, "Retiro realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            dashboardView.setVisible(true);
            currentView.dispose();
            
        } catch (InvalidAmountException | AccountNotFoundException | InsufficientFundsException | TellerOutOfCashException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Retiro", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
