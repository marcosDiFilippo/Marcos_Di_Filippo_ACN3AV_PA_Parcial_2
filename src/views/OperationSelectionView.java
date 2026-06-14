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
import constants.Colors;
import dto.BankTellerDTO;
import model.TransactionType;

public class OperationSelectionView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);

    private JPanel contentPane;
    private JLabel titleLabel;
    private JButton btnDeposit;
    private JButton btnWithdraw;
    private JButton btnBack;

    public OperationSelectionView(JFrame parentView, BankTellerDTO teller) {
        setTitle("Seleccionar Operación");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(backgroundColor);

        titleLabel = new JLabel("Seleccionar Operación");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(primaryColor);

        btnDeposit = new JButton("Depositar");
        btnDeposit.setFont(buttonFont);
        btnDeposit.setBackground(actionAccent);
        btnDeposit.setForeground(whiteColor);
        btnDeposit.setFocusPainted(false);
        btnDeposit.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnDeposit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeposit.setPreferredSize(new Dimension(250, 60));

        btnDeposit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AmountFormView form = new AmountFormView(parentView, teller, TransactionType.DEPOSITO);
                form.setVisible(true);
                dispose();
            }
        });

        btnWithdraw = new JButton("Retirar");
        btnWithdraw.setFont(buttonFont);
        btnWithdraw.setBackground(actionAccent);
        btnWithdraw.setForeground(whiteColor);
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        btnWithdraw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnWithdraw.setPreferredSize(new Dimension(250, 60));

        btnWithdraw.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AmountFormView form = new AmountFormView(parentView, teller, TransactionType.RETIRO);
                form.setVisible(true);
                dispose();
            }
        });

        btnBack = new JButton("Volver");
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

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 2;
        gbc1.insets = new Insets(20, 20, 40, 20);
        gbc1.anchor = GridBagConstraints.CENTER;
        contentPane.add(titleLabel, gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(10, 20, 20, 10);
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(btnDeposit, gbc2);

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 1;
        gbc3.gridy = 1;
        gbc3.insets = new Insets(10, 10, 20, 20);
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(btnWithdraw, gbc3);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 2;
        gbc4.gridwidth = 2;
        gbc4.insets = new Insets(30, 20, 20, 20);
        gbc4.anchor = GridBagConstraints.CENTER;
        contentPane.add(btnBack, gbc4);

        setContentPane(contentPane);
    }
}
