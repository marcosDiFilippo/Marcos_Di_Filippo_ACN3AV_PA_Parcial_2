package views;

import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import constants.Colors;
import controllers.AuthController;
import controllers.UserOperationController;
import controllers.BalanceController;
import controllers.TransferController;
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

    private JButton btnCheckBalance;
    private JButton btnDepositWithdraw;
    private JButton btnTransfer;

    public DashboardUser() {
        setTitle("Banco Sin Backup - Panel de Cliente");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        String userName = UserSession.getInstance() != null ? UserSession.getInstance().getFirstName() + " " + UserSession.getInstance().getLastName() : "Cliente";
        welcomeLabel = new JLabel("Bienvenido, " + userName);
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
                authController.logout(DashboardUser.this);
            }
        });

        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        headerPanel.add(btnLogout, BorderLayout.EAST);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(backgroundColor);

        btnCheckBalance = new JButton("Consultar Saldo y Movimientos");
        btnCheckBalance.setFont(buttonFont);
        btnCheckBalance.setBackground(actionAccent);
        btnCheckBalance.setForeground(whiteColor);
        btnCheckBalance.setFocusPainted(false);
        btnCheckBalance.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnCheckBalance.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCheckBalance.setPreferredSize(new Dimension(350, 60));

        btnCheckBalance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BalanceController balanceController = new BalanceController();
                balanceController.openBalanceView(DashboardUser.this);
            }
        });

        btnDepositWithdraw = new JButton("Depositar / Retirar Dinero");
        btnDepositWithdraw.setFont(buttonFont);
        btnDepositWithdraw.setBackground(actionAccent);
        btnDepositWithdraw.setForeground(whiteColor);
        btnDepositWithdraw.setFocusPainted(false);
        btnDepositWithdraw.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnDepositWithdraw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDepositWithdraw.setPreferredSize(new Dimension(350, 60));

        btnDepositWithdraw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserOperationController userOperationController = new UserOperationController();

                userOperationController.openTellerSelection(DashboardUser.this);
            }
        });

        btnTransfer = new JButton("Transferir a Otra Cuenta");
        btnTransfer.setFont(buttonFont);
        btnTransfer.setBackground(actionAccent);
        btnTransfer.setForeground(whiteColor);
        btnTransfer.setFocusPainted(false);
        btnTransfer.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnTransfer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTransfer.setPreferredSize(new Dimension(350, 60));

        btnTransfer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TransferController transferController = new TransferController();
                transferController.openTransferView(DashboardUser.this);
            }
        });

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(15, 15, 15, 15);
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnCheckBalance, gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(15, 15, 15, 15);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnDepositWithdraw, gbc2);

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.insets = new Insets(15, 15, 15, 15);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(btnTransfer, gbc3);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnCheckBalance() { return btnCheckBalance; }
    public JButton getBtnDepositWithdraw() { return btnDepositWithdraw; }
    public JButton getBtnTransfer() { return btnTransfer; }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DashboardUser frame = new DashboardUser();
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error inesperado.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
