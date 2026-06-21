package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.SwingConstants;

import constants.Colors;
import controllers.EmployeeController;

public class EmployeeTransactionMenuView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);

    private JPanel contentPane;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;

    private JLabel titleLabel;
    private JButton btnGeneral;
    private JButton btnTellers;
    private JButton btnTransfers;
    private JButton btnBack;

    public EmployeeTransactionMenuView(JFrame parentView) {
        setTitle("Menú de Transacciones");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(backgroundColor);

        northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(backgroundColor);
        northPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        titleLabel = new JLabel("Seleccione el Tipo de Historial", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);
        northPanel.add(titleLabel, BorderLayout.CENTER);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        btnGeneral = createMenuButton("Ver General");
        btnTellers = createMenuButton("Ver Cajeros");
        btnTransfers = createMenuButton("Ver Transferencias");

        btnGeneral.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeController controller = new EmployeeController();
                controller.viewAllTransactions(parentView);
                dispose();
            }
        });

        btnTellers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeController controller = new EmployeeController();
                controller.viewTellerTransactions(parentView);
                dispose();
            }
        });

        btnTransfers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EmployeeController controller = new EmployeeController();
                controller.viewTransferTransactions(parentView);
                dispose();
            }
        });

        gbc.gridy = 0;
        centerPanel.add(btnGeneral, gbc);

        gbc.gridy = 1;
        centerPanel.add(btnTellers, gbc);

        gbc.gridy = 2;
        centerPanel.add(btnTransfers, gbc);

        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        southPanel.setBackground(backgroundColor);

        btnBack = new JButton("Volver");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setBackground(secondaryGray);
        btnBack.setForeground(whiteColor);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentView.setVisible(true);
                dispose();
            }
        });

        southPanel.add(btnBack);

        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(southPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(buttonFont);
        btn.setBackground(actionAccent);
        btn.setForeground(whiteColor);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(300, 60));
        return btn;
    }
}
