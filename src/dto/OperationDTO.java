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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankTeller getTeller() {
        return teller;
    }

    public void setTeller(BankTeller teller) {
        this.teller = teller;
    }
}
