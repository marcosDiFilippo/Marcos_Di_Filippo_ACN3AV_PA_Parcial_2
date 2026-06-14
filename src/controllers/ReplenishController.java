package controllers;

import dto.BankTellerDTO;
import services.TellerService;
import views.ReplenishTellerSelectionView;
import views.ReplenishAmountView;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import model.BankTeller;

public class ReplenishController {

    private TellerService tellerService;

    public ReplenishController() {
        this.tellerService = new TellerService();
    }

    public void startReplenishFlow(JFrame parentView) {
        try {
            List<BankTeller> tellers = tellerService.getAllTellers();
            List<BankTellerDTO> dtos = new ArrayList<>();
            for (BankTeller t : tellers) {
                dtos.add(new BankTellerDTO(t.getId(), t.getLocation(), t.getAvailableCash()));
            }
            ReplenishTellerSelectionView view = new ReplenishTellerSelectionView(parentView, dtos);
            view.setVisible(true);
            parentView.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parentView, "Error al cargar cajeros: " + e.getMessage());
        }
    }

    public void processTellerSelection(BankTellerDTO selected, JFrame parentView) {
        ReplenishAmountView view = new ReplenishAmountView(parentView, selected);
        view.setVisible(true);
    }

    public void processReplenish(BankTellerDTO teller, double amount, JFrame currentView, JFrame dashboardView) {
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
