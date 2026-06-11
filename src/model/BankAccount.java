package model;

public class BankAccount extends User {
    private Long id;
    private Double balance;
    private Integer accountNumber;
    private String alias;
    
    @Override
    public boolean isEmployee() {
        return false;
    }
    
    public BankAccount() {}

    public BankAccount(Long id, Double balance, Integer accountNumber, String alias, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
        this.id = id;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.alias = alias;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    
    public Integer getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Integer accountNumber) { this.accountNumber = accountNumber; }
    
    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
}
