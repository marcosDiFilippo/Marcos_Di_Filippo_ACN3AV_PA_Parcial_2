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
import dto.OperationDTO;
import views.AmountFormView;
import views.DashboardUser;
import views.OperationSelectionView;

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

    public void processDeposit(OperationDTO dto, JFrame currentView) {
        try {
            transactionService.processDeposit(dto);
            JOptionPane.showMessageDialog(currentView, "Depósito realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            currentView.dispose();
            new DashboardUser().setVisible(true);
            
        } catch (InvalidAmountException | AccountNotFoundException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Depósito", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void processWithdraw(OperationDTO dto, JFrame currentView) {
        try {
            transactionService.processWithdraw(dto);
            JOptionPane.showMessageDialog(currentView, "Retiro realizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            currentView.dispose();
            new DashboardUser().setVisible(true);
            
        } catch (InvalidAmountException | AccountNotFoundException | InsufficientFundsException | TellerOutOfCashException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Retiro", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void goBackToOperationSelection(JFrame currentView, BankTeller teller) {
        UserOperationController userOpController = new UserOperationController();
        currentView.dispose();
        OperationSelectionView view = new OperationSelectionView(userOpController, teller);
        view.setVisible(true);
    }
}
