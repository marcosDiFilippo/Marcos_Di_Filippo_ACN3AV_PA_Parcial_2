package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import constants.Colors;

public class TransactionsView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

    private JPanel contentPane;
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JButton btnBack;
    private JScrollPane scrollPane;
    private JTable transactionsTable;

    public TransactionsView(JFrame parentView) {
        setTitle("Historial de Transacciones");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(backgroundColor);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        titleLabel = new JLabel("Historial de Transacciones");
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

        String[] columnNames = {"ID", "Fecha", "Tipo", "Monto", "Descripción", "ID Usuario"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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
        tableHeader.setPreferredSize(new java.awt.Dimension(0, 40));

        scrollPane = new JScrollPane(transactionsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        scrollPane.getViewport().setBackground(backgroundColor);

        contentPane.add(headerPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        setContentPane(contentPane);
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JTable getTransactionsTable() {
        return transactionsTable;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TransactionsView frame = new TransactionsView(null);
                frame.setVisible(true);
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(null, "Error al iniciar la vista: " + e.getMessage(), "Error Crítico", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
