package controllers;

import javax.swing.JOptionPane;

import services.TransactionService;
import views.TransferView;
import errors.AccountNotFoundException;
import errors.InsufficientFundsException;
import errors.InvalidAmountException;
import errors.SameAccountTransferException;

import javax.swing.JFrame;

public class TransferController {

    private TransactionService transactionService;

    public TransferController() {
        this.transactionService = new TransactionService();
    }

    public void openTransferView(JFrame currentView) {
        TransferView transferView = new TransferView(this);
        transferView.setVisible(true);
        currentView.dispose();
    }
    
    public void cancelTransfer(JFrame currentView) {
        currentView.dispose();
        new views.DashboardUser().setVisible(true);
    }

    public void processTransfer(double amount, String destinationInput, JFrame currentView) {
        try {
            transactionService.processTransfer(amount, destinationInput);
            JOptionPane.showMessageDialog(currentView, "Transferencia realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            currentView.dispose();
            new views.DashboardUser().setVisible(true);
            
        } catch (InvalidAmountException | AccountNotFoundException | InsufficientFundsException | SameAccountTransferException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Transferencia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
