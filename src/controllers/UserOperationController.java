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

    public void openTellerSelection(JFrame parentView) {
        List<BankTeller> tellers = tellerService.getAllTellers();
        TellerSelectionView view = new TellerSelectionView(parentView, tellers);
        view.setVisible(true);
        parentView.dispose();
    }

    public void processTellerSelection(BankTeller selected, JFrame parentView) {
        OperationSelectionView operationSelectionView = new OperationSelectionView(parentView, selected);
        operationSelectionView.setVisible(true);
    }
}
