package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.BankAccount;
import model.BankTeller;
import services.TellerService;
import services.TransactionService;
import session.UserSession;

class TransactionTest {

    private static TransactionService transactionService;
    private static TellerService tellerService;
    private static BankTeller testTeller;

    @BeforeAll
    static void setUp() throws Exception {
        transactionService = new TransactionService();
        tellerService = new TellerService();

        // Configuramos la sesión ficticia para el cliente 1 (Juan Perez)
        BankAccount account = new BankAccount();
        account.setUserId(1L);
        new UserSession(account);

        // Obtenemos un cajero real de la base de datos para las pruebas
        List<BankTeller> tellers = tellerService.getAllTellers();
        if (!tellers.isEmpty()) {
            testTeller = tellers.get(0);
        }
    }

    private void testFuncionalidadDeposito() throws Exception {
        assertDoesNotThrow(() -> {
            transactionService.processDeposit(500.0, testTeller);
        }, "El depósito de $500 debe procesarse sin arrojar excepciones.");
    }

    private void testFuncionalidadRetiroExitoso() throws Exception {
        assertDoesNotThrow(() -> {
            transactionService.processWithdraw(200.0, testTeller);
        }, "El retiro de $200 debe procesarse sin arrojar excepciones (ya que se depositaron fondos previamente).");
    }

    private void testFuncionalidadTransferencia() throws Exception {
        assertDoesNotThrow(() -> {
            // Transferimos a la cuenta de Maria Lopez (alias: maria.lopez.banco)
            transactionService.processTransfer(100.0, "maria.lopez.banco");
        }, "La transferencia de $100 a maria.lopez.banco debe procesarse correctamente.");
    }

    private void testValidacionRetiroSinFondos() {
        Exception exception = assertThrows(Exception.class, () -> {
            // Intentamos retirar una cantidad absurda de dinero
            transactionService.processWithdraw(10000000.0, testTeller);
        });
        assertTrue(exception.getMessage().contains("Saldo insuficiente"), 
            "El sistema debe bloquear y lanzar excepción por saldo insuficiente.");
    }

    private void testValidacionDepositoNegativo() {
        Exception exception = assertThrows(Exception.class, () -> {
            // Intentamos depositar dinero negativo
            transactionService.processDeposit(-50.0, testTeller);
        });
        assertTrue(exception.getMessage().contains("mayor a cero"), 
            "El sistema debe bloquear y lanzar excepción por monto de depósito inválido.");
    }

    // ==========================================
    // Función Orquestadora
    // ==========================================

    @Test
    void testFlujoOrquestadoDelCajero() {
        assertNotNull(testTeller, "Error crítico: No hay cajeros disponibles en la BD para probar.");

        try {
            // 1. Probamos validaciones de seguridad y lógica que NO alteran el saldo
            testValidacionRetiroSinFondos();
            testValidacionDepositoNegativo();

            // 2. Probamos operaciones transaccionales exitosas en secuencia lógica
            //    Para el cliente 1 (Juan Perez)
            testFuncionalidadDeposito();       // Ingresa +$500
            testFuncionalidadRetiroExitoso();  // Retira -$200
            testFuncionalidadTransferencia();  // Transfiere -$100

            // 3. Restauración de estado:
            //    Para mantener el saldo consistente en la base de datos (Ingresó 500, gastó 300),
            //    hacemos un retiro final de $200 para dejar la cuenta exactamente como estaba.
            transactionService.processWithdraw(200.0, testTeller);

            // Si llegamos hasta aquí sin que nada lance excepción, la orquestación fue un éxito.
            assertTrue(true); 

            System.out.println("flujo testeado exitosamente.");

        } catch (Exception e) {
            fail("Ha ocurrido un error inesperado.");
        }
    }
}
