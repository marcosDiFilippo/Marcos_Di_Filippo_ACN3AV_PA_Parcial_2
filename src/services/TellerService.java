package services;

import java.util.List;
import dao.BankTellerDAO;
import model.BankTeller;

public class TellerService {

    private BankTellerDAO bankTellerDAO;

    public TellerService() {
        this.bankTellerDAO = new BankTellerDAO();
    }

    public List<BankTeller> getAllTellers() {
        return bankTellerDAO.findAll();
    }
}
