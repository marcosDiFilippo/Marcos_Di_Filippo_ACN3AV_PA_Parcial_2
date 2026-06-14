package views;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import constants.Colors;
import controllers.DepositWithdrawController;
import dto.BankTellerDTO;

public class AmountFormView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);
    private final Font inputFont = new Font("Segoe UI", Font.PLAIN, 18);

    private JPanel contentPane;
    private JLabel titleLabel;
    private JTextField amountField;
    private JButton btnConfirm;
    private JButton btnBack;

    public AmountFormView(JFrame parentView, BankTellerDTO teller, String operationType) {
        setTitle(operationType.equals("DEPOSIT") ? "Depositar Dinero" : "Retirar Dinero");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(backgroundColor);

        titleLabel = new JLabel("Ingrese el monto a " + (operationType.equals("DEPOSIT") ? "depositar:" : "retirar:"));
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);

        amountField = new JTextField();
        amountField.setFont(inputFont);
        amountField.setPreferredSize(new Dimension(300, 50));
        amountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(secondaryGray, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        btnConfirm = new JButton("Confirmar");
        btnConfirm.setFont(buttonFont);
        btnConfirm.setBackground(actionAccent);
        btnConfirm.setForeground(whiteColor);
        btnConfirm.setFocusPainted(false);
        btnConfirm.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfirm.setPreferredSize(new Dimension(300, 60));

        btnConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    DepositWithdrawController controller = new DepositWithdrawController();
                    if (operationType.equals("DEPOSIT")) {
                        controller.processDeposit(amount, teller, AmountFormView.this, parentView);
                    } else {
                        controller.processWithdraw(amount, teller, AmountFormView.this, parentView);
                    }
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(AmountFormView.this, "Por favor ingrese un monto válido.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBack = new JButton("Cancelar");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setBackground(secondaryGray);
        btnBack.setForeground(whiteColor);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new Dimension(150, 40));

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentView.setVisible(true);
                dispose();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        contentPane.add(titleLabel, gbc);

        gbc.gridy = 1;
        contentPane.add(amountField, gbc);

        gbc.gridy = 2;
        contentPane.add(btnConfirm, gbc);

        gbc.gridy = 3;
        contentPane.add(btnBack, gbc);

        setContentPane(contentPane);
    }
}
