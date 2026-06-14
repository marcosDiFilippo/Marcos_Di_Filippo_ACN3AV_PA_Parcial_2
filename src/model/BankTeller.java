package model;

public class BankTeller {
    private Long id;
    private String location;
    private Double availableCash;

    public BankTeller() {}

    public BankTeller(Long id, String location, Double availableCash) {
        this.id = id;
        this.location = location;
        this.availableCash = availableCash;
    }

    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getLocation() { 
        return location; 
    }
    public void setLocation(String location) { 
        this.location = location; 
    }

    public Double getAvailableCash() { 
        return availableCash; 
    }
    public void setAvailableCash(Double availableCash) { 
        this.availableCash = availableCash; 
    }

    @Override
    public String toString() {
        return location;
    }
}
