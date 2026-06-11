package views;

import java.awt.BorderLayout;
import java.awt.Color;
import constants.Colors;
import model.BankAccount;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardUser extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel centerPanel;

    private JLabel welcomeLabel;
    private JButton btnLogout;

    private JButton btnConsultar;
    private JButton btnDepositarRetirar;
    private JButton btnTransferir;
    private JButton btnTestear;

    public DashboardUser() {
        setTitle("Sistema Bancario - Panel de Cliente");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        welcomeLabel = new JLabel("Bienvenido, Cliente");
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(whiteColor);

        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setBackground(secondaryGray);
        btnLogout.setForeground(whiteColor);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));

        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(btnLogout, BorderLayout.EAST);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(backgroundColor);

        btnConsultar = new JButton("Consultar Saldo y Movimientos");
        btnConsultar.setFont(buttonFont);
        btnConsultar.setBackground(actionAccent);
        btnConsultar.setForeground(whiteColor);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnConsultar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConsultar.setPreferredSize(new Dimension(350, 60));

        btnDepositarRetirar = new JButton("Depositar / Retirar Dinero");
        btnDepositarRetirar.setFont(buttonFont);
        btnDepositarRetirar.setBackground(actionAccent);
        btnDepositarRetirar.setForeground(whiteColor);
        btnDepositarRetirar.setFocusPainted(false);
        btnDepositarRetirar.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnDepositarRetirar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDepositarRetirar.setPreferredSize(new Dimension(350, 60));

        btnTransferir = new JButton("Transferir a Otra Cuenta");
        btnTransferir.setFont(buttonFont);
        btnTransferir.setBackground(actionAccent);
        btnTransferir.setForeground(whiteColor);
        btnTransferir.setFocusPainted(false);
        btnTransferir.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnTransferir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTransferir.setPreferredSize(new Dimension(350, 60));

        btnTestear = new JButton("Testear Funciones del Cajero");
        btnTestear.setFont(buttonFont);
        btnTestear.setBackground(actionAccent);
        btnTestear.setForeground(whiteColor);
        btnTestear.setFocusPainted(false);
        btnTestear.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnTestear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTestear.setPreferredSize(new Dimension(350, 60));

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(15, 15, 15, 15);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnConsultar, gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(15, 15, 15, 15);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnDepositarRetirar, gbc2);

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.insets = new Insets(15, 15, 15, 15);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnTransferir, gbc3);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 3;
        gbc4.insets = new Insets(15, 15, 15, 15);
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnTestear, gbc4);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnConsultar() { return btnConsultar; }
    public JButton getBtnDepositarRetirar() { return btnDepositarRetirar; }
    public JButton getBtnTransferir() { return btnTransferir; }
    public JButton getBtnTestear() { return btnTestear; }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DashboardUser frame = new DashboardUser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
