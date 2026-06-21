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

        BankAccount account = new BankAccount();
        account.setUserId(1L);
        new UserSession(account);

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
            transactionService.processTransfer(100.0, "maria.lopez.banco");
        }, "La transferencia de $100 a maria.lopez.banco debe procesarse correctamente.");
    }

    private void testValidacionRetiroSinFondos() {
        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.processWithdraw(10000000.0, testTeller);
        });
        assertTrue(exception.getMessage().contains("Saldo insuficiente"), 
            "El sistema debe bloquear y lanzar excepción por saldo insuficiente.");
    }

    private void testValidacionDepositoNegativo() {
        Exception exception = assertThrows(Exception.class, () -> {
            transactionService.processDeposit(-50.0, testTeller);
        });
        assertTrue(exception.getMessage().contains("mayor a cero"), 
            "El sistema debe bloquear y lanzar excepción por monto de depósito inválido.");
    }

    @Test
    void testFlujoOrquestadoDelCajero() {
        assertNotNull(testTeller, "Error crítico: No hay cajeros disponibles en la BD para probar.");

        try {
            testValidacionRetiroSinFondos();
            testValidacionDepositoNegativo();

            testFuncionalidadDeposito();     
            testFuncionalidadRetiroExitoso(); 
            testFuncionalidadTransferencia();

            transactionService.processWithdraw(200.0, testTeller);

            assertTrue(true); 

            System.out.println("flujo testeado exitosamente.");

        } catch (Exception e) {
            fail("Ha ocurrido un error inesperado.");
        }
    }
}
