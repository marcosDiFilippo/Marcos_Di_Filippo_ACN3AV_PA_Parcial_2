package dto;

public class BankTellerDTO {
    private long id;
    private String location;
    private double availableCash;

    public BankTellerDTO(long id, String location, double availableCash) {
        this.id = id;
        this.location = location;
        this.availableCash = availableCash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(double availableCash) {
        this.availableCash = availableCash;
    }

    @Override
    public String toString() {
        return location;
    }
}
