package controllers;

import java.util.List;
import javax.swing.JFrame;
import dao.BankTellerDAO;
import model.BankTeller;
import views.OperationSelectionView;
import views.TellerSelectionView;

public class UserOperationController {

    private BankTellerDAO bankTellerDAO;
    private BankTeller selectedTeller;

    public UserOperationController() {
        this.bankTellerDAO = new BankTellerDAO();
    }

    public void openTellerSelection(JFrame parentView) {
        List<BankTeller> tellers = bankTellerDAO.findAll();
        TellerSelectionView view = new TellerSelectionView(parentView, tellers);
        view.setVisible(true);
        parentView.dispose();
    }

    public void processTellerSelection(BankTeller selected, JFrame parentView) {
        this.selectedTeller = selected;
        OperationSelectionView operationSelectionView = new OperationSelectionView(parentView);
        operationSelectionView.setVisible(true);
    }
}
