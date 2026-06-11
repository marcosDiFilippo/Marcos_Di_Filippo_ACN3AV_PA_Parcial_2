package model;

public class BankEmployee extends User {
    private Long id;
    
    @Override
    public boolean isEmployee() {
        return true;
    }
    
    public BankEmployee() {}

    public BankEmployee(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, password);
        this.id = id;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
}
