package views;

import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import constants.Colors;
import controllers.AuthController;
import controllers.EmployeeController;
import controllers.ReplenishController;
import session.UserSession;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardEmployee extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

    private JPanel contentPane;
    private JPanel headerPanel;
    private JPanel centerPanel;

    private JLabel welcomeLabel;
    private JButton btnLogout;

    private JButton btnVerTransacciones;
    private JButton btnVerSaldosCajeros;
    private JButton btnReponerDinero;
    private JButton btnEstadisticas;

    public DashboardEmployee() {
        setTitle("Sistema Bancario - Panel de Empleado");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(backgroundColor);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        String employeeName = UserSession.getInstance() != null ? UserSession.getInstance().getFirstName() + " " + UserSession.getInstance().getLastName() : "Empleado";
        welcomeLabel = new JLabel("Panel de Administración - Bienvenido, " + employeeName);
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(whiteColor);

        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setBackground(primaryColor);
        btnLogout.setForeground(whiteColor);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               AuthController authController = new AuthController();
               authController.logout(DashboardEmployee.this);
            }
        });

        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(btnLogout, BorderLayout.EAST);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(backgroundColor);

        btnVerTransacciones = new JButton("Visualizar Todas las Transacciones");
        btnVerTransacciones.setFont(buttonFont);
        btnVerTransacciones.setBackground(actionAccent);
        btnVerTransacciones.setForeground(whiteColor);
        btnVerTransacciones.setFocusPainted(false);
        btnVerTransacciones.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnVerTransacciones.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerTransacciones.setPreferredSize(new Dimension(350, 60));

        btnVerTransacciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeController employeeController = new EmployeeController(DashboardEmployee.this);
                employeeController.openTransactionMenu();
                dispose();
            }
        });

        btnVerSaldosCajeros = new JButton("Ver Saldo de Cajeros");
        btnVerSaldosCajeros.setFont(buttonFont);
        btnVerSaldosCajeros.setBackground(actionAccent);
        btnVerSaldosCajeros.setForeground(whiteColor);
        btnVerSaldosCajeros.setFocusPainted(false);
        btnVerSaldosCajeros.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnVerSaldosCajeros.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerSaldosCajeros.setPreferredSize(new Dimension(350, 60));

        btnVerSaldosCajeros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeController employeeController = new EmployeeController(DashboardEmployee.this);
                employeeController.viewTellersBalance();
            }
        });

        btnReponerDinero = new JButton("Reponer Dinero en Cajero");
        btnReponerDinero.setFont(buttonFont);
        btnReponerDinero.setBackground(actionAccent);
        btnReponerDinero.setForeground(whiteColor);
        btnReponerDinero.setFocusPainted(false);
        btnReponerDinero.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnReponerDinero.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReponerDinero.setPreferredSize(new Dimension(350, 60));

        btnReponerDinero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ReplenishController replenishController = new ReplenishController();
                replenishController.startReplenishFlow(DashboardEmployee.this);
            }
        });

        btnEstadisticas = new JButton("Generar Estadísticas del Día");
        btnEstadisticas.setFont(buttonFont);
        btnEstadisticas.setBackground(actionAccent);
        btnEstadisticas.setForeground(whiteColor);
        btnEstadisticas.setFocusPainted(false);
        btnEstadisticas.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnEstadisticas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEstadisticas.setPreferredSize(new Dimension(350, 60));

        btnEstadisticas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeController employeeController = new EmployeeController(DashboardEmployee.this);
                employeeController.viewStatistics();
            }
        });

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(15, 15, 15, 15);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnVerTransacciones, gbc1);

        GridBagConstraints gbc_saldo = new GridBagConstraints();
        gbc_saldo.gridx = 0;
        gbc_saldo.gridy = 1;
        gbc_saldo.insets = new Insets(15, 15, 15, 15);
        gbc_saldo.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnVerSaldosCajeros, gbc_saldo);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.insets = new Insets(15, 15, 15, 15);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnReponerDinero, gbc2);

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 3;
        gbc3.insets = new Insets(15, 15, 15, 15);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnEstadisticas, gbc3);

        contentPane.add(headerPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnVerTransacciones() { return btnVerTransacciones; }
    public JButton getBtnVerSaldosCajeros() { return btnVerSaldosCajeros; }
    public JButton getBtnReponerDinero() { return btnReponerDinero; }
    public JButton getBtnEstadisticas() { return btnEstadisticas; }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DashboardEmployee frame = new DashboardEmployee();
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
