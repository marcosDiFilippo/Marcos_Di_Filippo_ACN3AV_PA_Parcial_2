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

    public void openTransferView(JFrame parentView) {
        TransferView transferView = new TransferView(parentView);
        transferView.setVisible(true);
        parentView.dispose();
    }

    public void processTransfer(double amount, String destinationInput, JFrame currentView, JFrame dashboardView) {
        try {
            transactionService.processTransfer(amount, destinationInput);
            JOptionPane.showMessageDialog(currentView, "Transferencia realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            dashboardView.setVisible(true);
            currentView.dispose();
            
        } catch (InvalidAmountException | AccountNotFoundException | InsufficientFundsException | SameAccountTransferException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Error en Transferencia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
