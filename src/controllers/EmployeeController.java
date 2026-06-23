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

    public void openTransactionMenu(JFrame currentView) {
        EmployeeTransactionMenuView menuView = new EmployeeTransactionMenuView(this);
        menuView.setVisible(true);
        currentView.dispose();
    }

    public void viewAllTransactions(JFrame currentView) {
        try {
            List<Transaction> list = transactionService.getAllTransactions();
            TransactionsView view = new TransactionsView(this);
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
            currentView.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.");
        }
    }

    public void viewTellerTransactions(JFrame currentView) {
        try {
            List<Transaction> list = transactionService.getTellerTransactions();
            TransactionsView view = new TransactionsView(this);
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
            currentView.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.");
        }
    }

    public void viewTransferTransactions(JFrame currentView) {
        try {
            List<Transaction> list = transactionService.getTransferTransactions();
            TransactionsView view = new TransactionsView(this);
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
            currentView.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.");
        }
    }

    public void viewStatistics(JFrame currentView) {
        try {
            Map<String, Integer> stats = transactionService.getTransactionStatistics();
            if (stats.isEmpty()) {
                JOptionPane.showMessageDialog(currentView, "No hay transacciones registradas aún.", "Estadísticas", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("Resumen de Operaciones (Fin del Día):\n\n");
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                sb.append("• ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" transacciones\n");
            }
            
            JOptionPane.showMessageDialog(currentView, sb.toString(), "Estadísticas Diarias", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void viewTellersBalance(JFrame currentView) {
        try {
            TellerService tellerService = new TellerService();
            List<BankTeller> tellers = tellerService.getAllTellers();
            TellersBalanceView view = new TellersBalanceView(this, tellers);
            currentView.dispose();
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void goBackToDashboard(JFrame currentView) {
        currentView.dispose();
        new views.DashboardEmployee().setVisible(true);
    }

    public void goBackToTransactionMenu(JFrame currentView) {
        openTransactionMenu(currentView);
    }
}
