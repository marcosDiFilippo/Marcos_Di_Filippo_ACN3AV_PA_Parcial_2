package views;

import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import constants.*;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
    private final Font subtitleFont = new Font("Segoe UI", Font.PLAIN, 16);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
    private final Font footerFont = new Font("Segoe UI", Font.ITALIC, 12);
    private final Font logoFont = new Font("Segoe UI", Font.BOLD, 20);

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel heroPanel;
    private JPanel footerPanel;

    private JLabel logoLabel;
    private JButton loginButton;

    private JLabel welcomeTitleLabel;
    private JLabel welcomeSubtitleLabel;
    private JButton accessButton;

    private JLabel footerLabel;
    
    public Home() {
    	setTitle("Sistema Bancario - Inicio");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        heroPanel = new JPanel(new GridBagLayout());
        heroPanel.setBackground(backgroundColor);

        footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(primaryColor);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        logoLabel = new JLabel("Sistema Bancario");
        logoLabel.setFont(logoFont);
        logoLabel.setForeground(whiteColor);

        loginButton = new JButton("Iniciar Sesión");
        loginButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Login login = new Login();
        		login.setVisible(true);
        		dispose();
        	}
        });
        loginButton.setFont(buttonFont);
        loginButton.setBackground(actionAccent);
        loginButton.setForeground(whiteColor);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        welcomeTitleLabel = new JLabel("Bienvenido a Bank System");
        welcomeTitleLabel.setFont(titleFont);
        welcomeTitleLabel.setForeground(primaryColor);
        welcomeTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        welcomeSubtitleLabel = new JLabel("Realice consultas, depósitos, retiros y transferencias de manera segura.");
        welcomeSubtitleLabel.setFont(subtitleFont);
        welcomeSubtitleLabel.setForeground(secondaryGray);
        welcomeSubtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        accessButton = new JButton("Acceder al Sistema");
        accessButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        accessButton.setBackground(primaryColor);
        accessButton.setForeground(whiteColor);
        accessButton.setFocusPainted(false);
        accessButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        accessButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accessButton.setPreferredSize(new Dimension(250, 45));

        accessButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login login = new Login();
                login.setVisible(true);
                dispose();
            }
        });

        footerLabel = new JLabel("Marcos Di Filippo");
        footerLabel.setFont(footerFont);
        footerLabel.setForeground(whiteColor);
        
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(loginButton, BorderLayout.EAST);

        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.insets = new Insets(10, 10, 20, 10);
        gbc1.anchor = GridBagConstraints.CENTER;
        heroPanel.add(welcomeTitleLabel, gbc1);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.insets = new Insets(0, 10, 40, 10);
        gbc2.anchor = GridBagConstraints.CENTER;
        heroPanel.add(welcomeSubtitleLabel, gbc2);

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.insets = new Insets(0, 10, 10, 10);
        gbc3.anchor = GridBagConstraints.CENTER;
        heroPanel.add(accessButton, gbc3);

        footerPanel.add(footerLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(heroPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
    	EventQueue.invokeLater(() -> {
			try {
				Home frame = new Home();
				frame.setVisible(true);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al iniciar la vista: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

}
