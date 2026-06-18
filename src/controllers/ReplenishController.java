package controllers;

import javax.swing.JOptionPane;

import model.BankTeller;
import services.TellerService;
import views.ReplenishTellerSelectionView;
import views.ReplenishAmountView;

import javax.swing.JFrame;
import java.util.List;

public class ReplenishController {

    private TellerService tellerService;

    public ReplenishController() {
        this.tellerService = new TellerService();
    }

    public void startReplenishFlow(JFrame parentView) {
        try {
            List<BankTeller> tellers = tellerService.getAllTellers();
            ReplenishTellerSelectionView view = new ReplenishTellerSelectionView(parentView, tellers);
            view.setVisible(true);
            parentView.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Error al cargar cajeros: " + e.getMessage());
        }
    }

    public void processTellerSelection(BankTeller selected, JFrame parentView) {
        ReplenishAmountView view = new ReplenishAmountView(parentView, selected);
        view.setVisible(true);
    }

    public void processReplenish(BankTeller teller, double amount, JFrame currentView, JFrame dashboardView) {
        try {
            tellerService.replenishCash(teller, amount);
            JOptionPane.showMessageDialog(currentView, "Dinero repuesto exitosamente.");
            currentView.dispose();
            dashboardView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(currentView, "Error al reponer dinero: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
