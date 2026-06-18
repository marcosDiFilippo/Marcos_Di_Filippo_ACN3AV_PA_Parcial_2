package errors;

public class TellerOutOfCashException extends Exception {
    private static final long serialVersionUID = 1L;

    public TellerOutOfCashException(String message) {
        super(message);
    }
}
