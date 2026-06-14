package controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;
import model.Transaction;
import services.TransactionService;
import views.TransactionsView;
import views.EmployeeTransactionMenuView;
import javax.swing.table.DefaultTableModel;

public class EmployeeController {

    private JFrame parentView;
    private TransactionService transactionService;

    public EmployeeController(JFrame parentView) {
        this.parentView = parentView;
        this.transactionService = new TransactionService();
    }

    public void openTransactionMenu() {
        EmployeeTransactionMenuView menuView = new EmployeeTransactionMenuView(parentView);
        menuView.setVisible(true);
    }

    public void viewAllTransactions() {
        try {
            List<Transaction> list = transactionService.getAllTransactions();
            TransactionsView view = new TransactionsView(parentView);
            DefaultTableModel model = (DefaultTableModel) view.getTransactionsTable().getModel();
            for (Transaction t : list) {
                model.addRow(new Object[]{
                    t.getId(),
                    t.getCreatedAt(),
                    t.getTypeName(),
                    t.getAmount(),
                    t.getDescription(),
                    t.getUserId()
                });
            }
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Error al cargar transacciones: " + e.getMessage());
        }
    }

    public void viewTellerTransactions() {
        try {
            List<Transaction> list = transactionService.getTellerTransactions();
            TransactionsView view = new TransactionsView(parentView);
            DefaultTableModel model = (DefaultTableModel) view.getTransactionsTable().getModel();
            for (Transaction t : list) {
                model.addRow(new Object[]{
                    t.getId(),
                    t.getCreatedAt(),
                    t.getTypeName(),
                    t.getAmount(),
                    t.getDescription(),
                    t.getUserId()
                });
            }
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Error al cargar transacciones: " + e.getMessage());
        }
    }

    public void viewTransferTransactions() {
        try {
            List<Transaction> list = transactionService.getTransferTransactions();
            TransactionsView view = new TransactionsView(parentView);
            DefaultTableModel model = (DefaultTableModel) view.getTransactionsTable().getModel();
            for (Transaction t : list) {
                model.addRow(new Object[]{
                    t.getId(),
                    t.getCreatedAt(),
                    t.getTypeName(),
                    t.getAmount(),
                    t.getDescription(),
                    t.getUserId()
                });
            }
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Error al cargar transacciones: " + e.getMessage());
        }
    }
}
