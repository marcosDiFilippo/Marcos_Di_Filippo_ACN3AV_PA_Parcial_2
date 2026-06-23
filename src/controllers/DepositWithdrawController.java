package controllers;

import javax.swing.JOptionPane;

import model.BankTeller;
import services.TransactionService;
import errors.AccountNotFoundException;
import errors.InsufficientFundsException;
import errors.InvalidAmountException;
import errors.TellerOutOfCashException;

import javax.swing.JFrame;
import constants.TransactionType;
import views.AmountFormView;

public class DepositWithdrawController {

    private TransactionService transactionService;

    public DepositWithdrawController() {
        this.transactionService = new TransactionService();
    }

    public void openAmountForm(JFrame currentView, BankTeller teller, TransactionType type) {
        AmountFormView amountFormView = new AmountFormView(this, teller, type);
        amountFormView.setVisible(true);
        currentView.dispose();
    }

    public void processDeposit(double amount, BankTeller teller, JFrame currentView) {
        try {
            transactionService.processDeposit(amount, teller);
            JOptionPane.showMessageDialog(currentView, "Depósito realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            currentView.dispose();
            new views.DashboardUser().setVisible(true);
            
        } catch (InvalidAmountException | AccountNotFoundException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Depósito", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void processWithdraw(double amount, BankTeller teller, JFrame currentView) {
        try {
            transactionService.processWithdraw(amount, teller);
            JOptionPane.showMessageDialog(currentView, "Retiro realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            currentView.dispose();
            new views.DashboardUser().setVisible(true);
            
        } catch (InvalidAmountException | AccountNotFoundException | InsufficientFundsException | TellerOutOfCashException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Retiro", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void goBackToOperationSelection(JFrame currentView, BankTeller teller) {
        controllers.UserOperationController userOpController = new controllers.UserOperationController();
        currentView.dispose();
        views.OperationSelectionView view = new views.OperationSelectionView(userOpController, teller);
        view.setVisible(true);
    }
}
