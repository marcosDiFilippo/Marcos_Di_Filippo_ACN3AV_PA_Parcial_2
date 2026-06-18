package controllers;

import services.TransactionService;
import views.TransferView;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Error al procesar la transferencia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
