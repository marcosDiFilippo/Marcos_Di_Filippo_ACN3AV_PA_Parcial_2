package controllers;

import javax.swing.JOptionPane;

import model.BankTeller;
import services.TellerService;
import views.ReplenishTellerSelectionView;
import views.ReplenishAmountView;
import errors.InvalidAmountException;

import javax.swing.JFrame;
import java.util.List;

public class ReplenishController {

    private TellerService tellerService;

    public ReplenishController() {
        this.tellerService = new TellerService();
    }

    public void startReplenishFlow(JFrame currentView) {
        try {
            List<BankTeller> tellers = tellerService.getAllTellers();
            ReplenishTellerSelectionView view = new ReplenishTellerSelectionView(this, tellers);
            view.setVisible(true);
            currentView.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.");
        }
    }

    public void processTellerSelection(BankTeller selected, JFrame currentView) {
        ReplenishAmountView view = new ReplenishAmountView(this, selected);
        view.setVisible(true);
        currentView.dispose();
    }

    public void processReplenish(BankTeller teller, double amount, JFrame currentView) {
        try {
            tellerService.replenishCash(teller, amount);
            JOptionPane.showMessageDialog(currentView, "Dinero repuesto exitosamente.");
            currentView.dispose();
            new views.DashboardEmployee().setVisible(true);
        } catch (InvalidAmountException e) {
            JOptionPane.showMessageDialog(currentView, e.getMessage(), "Monto Inválido", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Ha ocurrido un error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void goBackToDashboard(JFrame currentView) {
        currentView.dispose();
        new views.DashboardEmployee().setVisible(true);
    }

    public void goBackToTellerSelection(JFrame currentView) {
        startReplenishFlow(currentView);
    }
}
