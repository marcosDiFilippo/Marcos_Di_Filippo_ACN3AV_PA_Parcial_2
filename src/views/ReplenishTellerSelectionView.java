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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import constants.Colors;
import controllers.ReplenishController;
import dto.BankTellerDTO;

public class ReplenishTellerSelectionView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

    private JPanel contentPane;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel southPanel;

    private JLabel titleLabel;
    private JButton btnBack;

    public ReplenishTellerSelectionView(JFrame parentView, List<BankTellerDTO> tellers) {
        setTitle("Seleccionar Cajero para Reponer Dinero");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(backgroundColor);

        northPanel = new JPanel(new BorderLayout());
        northPanel.setBackground(primaryColor);
        northPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        titleLabel = new JLabel("Seleccione un cajero para reponer dinero", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(whiteColor);
        northPanel.add(titleLabel, BorderLayout.CENTER);

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20);

        for (int i = 0; i < tellers.size(); i++) {
            BankTellerDTO tellerActual = tellers.get(i);
            JButton btnTeller = new JButton(tellerActual.toString());
            btnTeller.setFont(buttonFont);
            btnTeller.setBackground(actionAccent);
            btnTeller.setForeground(whiteColor);
            btnTeller.setFocusPainted(false);
            btnTeller.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
            btnTeller.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnTeller.setPreferredSize(new Dimension(300, 50));
            btnTeller.setToolTipText(tellerActual.toString());

            btnTeller.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    ReplenishController controller = new ReplenishController();
                    controller.processTellerSelection(tellerActual, parentView);
                    dispose();
                }
            });

            gbc.gridy = i;
            centerPanel.add(btnTeller, gbc);
        }

        southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        southPanel.setBackground(backgroundColor);

        btnBack = new JButton("Volver");
        btnBack.setFont(buttonFont);
        btnBack.setBackground(secondaryGray);
        btnBack.setForeground(whiteColor);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentView.setVisible(true);
                dispose();
            }
        });

        southPanel.add(btnBack);

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(centerPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getViewport().setBackground(backgroundColor);

        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(southPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }
}
