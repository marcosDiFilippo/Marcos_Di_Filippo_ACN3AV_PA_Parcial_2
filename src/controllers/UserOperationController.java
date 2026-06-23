package controllers;

import java.util.List;
import javax.swing.JFrame;
import model.BankTeller;
import views.OperationSelectionView;
import views.TellerSelectionView;
import services.TellerService;

public class UserOperationController {

    private TellerService tellerService;

    public UserOperationController() {
        this.tellerService = new TellerService();
    }

    public void openTellerSelection(JFrame currentView) {
        List<BankTeller> tellers = tellerService.getAllTellers();
        TellerSelectionView view = new TellerSelectionView(this, tellers);
        view.setVisible(true);
        currentView.dispose();
    }

    public void processTellerSelection(BankTeller selected, JFrame currentView) {
        OperationSelectionView operationSelectionView = new OperationSelectionView(this, selected);
        operationSelectionView.setVisible(true);
        currentView.dispose();
    }

    public void goBackToDashboard(JFrame currentView) {
        currentView.dispose();
        new views.DashboardUser().setVisible(true);
    }

    public void goBackToTellerSelection(JFrame currentView) {
        openTellerSelection(currentView);
    }
}
