package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dto.OperationDTO;
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

    private void testFuncionalidadDeposito() {
        try {
            transactionService.processDeposit(new OperationDTO(500.0, testTeller));
            assertTrue(true, "El depósito de $500 funcionó sin problemas");
        } catch (Exception e) {
            fail("algo falló al intentar depositar los $500: " + e.getMessage());
        }
    }

    private void testFuncionalidadRetiroExitoso() {
        try {
            transactionService.processWithdraw(new OperationDTO(200.0, testTeller));
            assertTrue(true, "El retiro de $200 salió bien");
        } catch (Exception e) {
            fail("Hubo un problema al retirar los $200: " + e.getMessage());
        }
    }

    private void testFuncionalidadTransferencia() {
        try {
            transactionService.processTransfer(100.0, "maria.lopez.banco");
            assertTrue(true, "La transferencia de $100 llegó a su destino");
        } catch (Exception e) {
            fail("No se pudo completar la transferencia: " + e.getMessage());
        }
    }

    private void testValidacionRetiroSinFondos() {
        try {
            transactionService.processWithdraw(new OperationDTO(10000000.0, testTeller));
            fail("el retiro debería haber fallado porque la cuenta no tiene mucho dinero");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Saldo insuficiente"), "Esperábamos un error por falta de saldo, pero saltó otra cosa.");
        }
    }

    private void testValidacionDepositoNegativo() {
        try {
            transactionService.processDeposit(new OperationDTO(-50.0, testTeller));
            fail("Debería haber fallado al intentar depositar un monto negativo.");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("mayor a cero"), "El error debería habernos avisado que el monto no puede ser negativo.");
        }
    }

    @Test
    void testFlujoOrquestadoDelCajero() {
        assertNotNull(testTeller, "No pudimos encontrar ningún cajero en la base de datos para hacer la prueba.");

        try {
            testValidacionRetiroSinFondos();
            testValidacionDepositoNegativo();

            testFuncionalidadDeposito();     
            testFuncionalidadRetiroExitoso(); 
            testFuncionalidadTransferencia();

            transactionService.processWithdraw(new OperationDTO(200.0, testTeller));

            assertTrue(true); 

            System.out.println("Todo el recorrido del usuario en el cajero se probó con éxito");

        } catch (Exception e) {
            fail("Algo rompio la prueba general.");
        }
    }
}
