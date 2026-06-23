package dto;

import model.BankTeller;

public class OperationDTO {
    private double amount;
    private BankTeller teller;

    public OperationDTO(double amount, BankTeller teller) {
        this.amount = amount;
        this.teller = teller;
    }

    public double getAmount() {
        return amount;
    }

    public BankTeller getTeller() {
        return teller;
    }
}
