package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import constants.Colors;
import dto.TransactionDTO;

public class BalanceView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private final Font balanceFont = new Font("Segoe UI", Font.BOLD, 36);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

    private JPanel contentPane;
    private JPanel headerPanel;
    private JPanel balancePanel;
    private JLabel titleLabel;
    private JLabel balanceLabel;
    private JButton btnBack;
    private JScrollPane scrollPane;
    private JTable transactionsTable;

    public BalanceView(JFrame parentView, double currentBalance, List<TransactionDTO> transactions) {
        setTitle("Saldo y Movimientos");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(backgroundColor);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        titleLabel = new JLabel("Saldo y Movimientos");
        titleLabel.setFont(titleFont);
        titleLabel.setForeground(whiteColor);

        btnBack = new JButton("Volver al Menú");
        btnBack.setFont(buttonFont);
        btnBack.setBackground(primaryColor);
        btnBack.setForeground(whiteColor);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentView.setVisible(true);
                dispose();
            }
        });

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(btnBack, BorderLayout.EAST);

        balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(backgroundColor);
        balancePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        balanceLabel = new JLabel(String.format("Saldo Actual: $%.2f", currentBalance));
        balanceLabel.setFont(balanceFont);
        balanceLabel.setForeground(primaryColor);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balancePanel.add(balanceLabel, BorderLayout.CENTER);

        String[] columnNames = {"ID", "Fecha", "Tipo", "Monto", "Descripción"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (TransactionDTO t : transactions) {
            model.addRow(new Object[]{
                t.getId(),
                t.getDate().toString(),
                t.getType(),
                String.format("$%.2f", t.getAmount()),
                t.getDescription()
            });
        }

        transactionsTable = new JTable(model);
        transactionsTable.setFillsViewportHeight(true);
        transactionsTable.setRowHeight(30);
        transactionsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        transactionsTable.setBackground(whiteColor);
        transactionsTable.setGridColor(secondaryGray);

        JTableHeader tableHeader = transactionsTable.getTableHeader();
        tableHeader.setBackground(primaryColor);
        tableHeader.setForeground(whiteColor);
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableHeader.setPreferredSize(new Dimension(0, 40));

        scrollPane = new JScrollPane(transactionsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        scrollPane.getViewport().setBackground(backgroundColor);

        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.setBackground(backgroundColor);
        centerContainer.add(balancePanel, BorderLayout.NORTH);
        centerContainer.add(scrollPane, BorderLayout.CENTER);

        contentPane.add(headerPanel, BorderLayout.NORTH);
        contentPane.add(centerContainer, BorderLayout.CENTER);

        setContentPane(contentPane);
    }
}
