package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import dto.BankTellerDTO;
import model.BankTeller;
import views.OperationSelectionView;
import views.TellerSelectionView;
import services.TellerService;

public class UserOperationController {

    private TellerService tellerService;
    private BankTellerDTO selectedTeller;

    public UserOperationController() {
        this.tellerService = new TellerService();
    }

    public void openTellerSelection(JFrame parentView) {
        List<BankTeller> tellers = tellerService.getAllTellers();
        List<BankTellerDTO> dtos = new ArrayList<>();
        for (BankTeller t : tellers) {
            dtos.add(new BankTellerDTO(t.getId(), t.getLocation(), t.getAvailableCash()));
        }
        TellerSelectionView view = new TellerSelectionView(parentView, dtos);
        view.setVisible(true);
        parentView.dispose();
    }

    public void processTellerSelection(BankTellerDTO selected, JFrame parentView) {
        this.selectedTeller = selected;
        OperationSelectionView operationSelectionView = new OperationSelectionView(parentView, selected);
        operationSelectionView.setVisible(true);
    }
}
