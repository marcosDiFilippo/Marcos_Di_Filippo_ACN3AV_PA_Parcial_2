package controllers;

import javax.swing.JOptionPane;

import javax.swing.JFrame;
import java.util.List;
import model.Transaction;
import services.TransactionService;
import views.TransactionsView;
import views.EmployeeTransactionMenuView;
import views.TellersBalanceView;
import javax.swing.table.DefaultTableModel;
import services.TellerService;
import model.BankTeller;
import java.util.Map;

public class EmployeeController {

    private TransactionService transactionService;

    public EmployeeController() {
        this.transactionService = new TransactionService();
    }

    public void openTransactionMenu(JFrame parentView) {
        EmployeeTransactionMenuView menuView = new EmployeeTransactionMenuView(parentView);
        menuView.setVisible(true);
    }

    public void viewAllTransactions(JFrame parentView) {
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
            JOptionPane.showMessageDialog(parentView, "Ha ocurrido un error inesperado.");
        }
    }

    public void viewTellerTransactions(JFrame parentView) {
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
            JOptionPane.showMessageDialog(parentView, "Ha ocurrido un error inesperado.");
        }
    }

    public void viewTransferTransactions(JFrame parentView) {
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
            JOptionPane.showMessageDialog(parentView, "Ha ocurrido un error inesperado.");
        }
    }

    public void viewStatistics(JFrame parentView) {
        try {
            Map<String, Integer> stats = transactionService.getTransactionStatistics();
            if (stats.isEmpty()) {
                JOptionPane.showMessageDialog(parentView, "No hay transacciones registradas aún.", "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("Resumen de Operaciones (Fin del Día):\n\n");
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                sb.append("• ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" transacciones\n");
            }
            
            JOptionPane.showMessageDialog(parentView, sb.toString(), "Estadísticas Diarias", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void viewTellersBalance(JFrame parentView) {
        try {
            TellerService tellerService = new TellerService();
            List<BankTeller> tellers = tellerService.getAllTellers();
            TellersBalanceView view = new TellersBalanceView(parentView, tellers);
            parentView.setVisible(false);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
