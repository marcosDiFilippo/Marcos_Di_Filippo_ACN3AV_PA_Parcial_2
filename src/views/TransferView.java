package views;

import javax.swing.JOptionPane;

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
import controllers.TransferController;

public class TransferView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
    private final Font labelFont = new Font("Segoe UI", Font.BOLD, 16);
    private final Font inputFont = new Font("Segoe UI", Font.PLAIN, 18);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);

    private JPanel contentPane;
    private JLabel titleLabel;
    private JLabel lblDestination;
    private JTextField txtDestination;
    private JLabel lblAmount;
    private JTextField txtAmount;
    private JButton btnTransfer;
    private JButton btnBack;

    public TransferView(JFrame parentView) {
        setTitle("Transferir Dinero");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(backgroundColor);

        titleLabel = new JLabel("Transferencia");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);

        lblDestination = new JLabel("Cuenta Destino (N° de cuenta o Alias):");
        lblDestination.setFont(labelFont);
        lblDestination.setForeground(primaryColor);

        txtDestination = new JTextField();
        txtDestination.setFont(inputFont);
        txtDestination.setPreferredSize(new Dimension(350, 40));
        txtDestination.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(secondaryGray, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        lblAmount = new JLabel("Monto a transferir:");
        lblAmount.setFont(labelFont);
        lblAmount.setForeground(primaryColor);

        txtAmount = new JTextField();
        txtAmount.setFont(inputFont);
        txtAmount.setPreferredSize(new Dimension(350, 40));
        txtAmount.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(secondaryGray, 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        btnTransfer = new JButton("Confirmar Transferencia");
        btnTransfer.setFont(buttonFont);
        btnTransfer.setBackground(actionAccent);
        btnTransfer.setForeground(whiteColor);
        btnTransfer.setFocusPainted(false);
        btnTransfer.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnTransfer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTransfer.setPreferredSize(new Dimension(350, 50));

        btnTransfer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    double amount = Double.parseDouble(txtAmount.getText());
                    String destination = txtDestination.getText().trim();
                    if (destination.isEmpty()) {
                        JOptionPane.showMessageDialog(TransferView.this, "Por favor ingrese la cuenta destino.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    TransferController controller = new TransferController();
                    controller.processTransfer(amount, destination, TransferView.this, parentView);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TransferView.this, "Por favor ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
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
        gbc.insets = new Insets(20, 20, 30, 20);
        contentPane.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 20, 5, 20);
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(lblDestination, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 20, 20, 20);
        contentPane.add(txtDestination, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(10, 20, 5, 20);
        contentPane.add(lblAmount, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 20, 30, 20);
        contentPane.add(txtAmount, gbc);

        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 20, 20, 20);
        contentPane.add(btnTransfer, gbc);

        gbc.gridy = 6;
        contentPane.add(btnBack, gbc);

        setContentPane(contentPane);
    }
}
