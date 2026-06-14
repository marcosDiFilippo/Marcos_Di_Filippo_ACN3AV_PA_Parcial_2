package constants;

public enum TransactionType {
    DEPOSITO("Deposito"),
    RETIRO("Retiro"),
    TRANSFERENCIA("Transferencia"),
    CONSULTA_SALDO("Consulta Saldo"),
    REPOSICION_CAJERO("Reposicion Cajero"),
    OPERACION_PRUEBA("Operacion Prueba");

    private final String dbName;

    TransactionType(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }
}
