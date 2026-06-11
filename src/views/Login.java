package views;

import java.awt.BorderLayout;
import java.awt.Color;
import constants.Colors;
import java.awt.Cursor;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controllers.AuthController;
import dto.Auth.LoginDTO;
import errors.InvalidCredentialsException;
import errors.ValidationException;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;

    private final Color primaryColor = Colors.PRIMARY_COLOR.getColor();
    private final Color secondaryGray = Colors.SECONDARY_GRAY.getColor();
    private final Color backgroundColor = Colors.BACKGROUND_COLOR.getColor();
    private final Color actionAccent = Colors.ACTION_ACCENT.getColor();
    private final Color whiteColor = Colors.WHITE_COLOR.getColor();
    private final Color inputBorderColor = Colors.INPUT_BORDER_COLOR.getColor();

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
    private final Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
    private final Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
    private final Font logoFont = new Font("Segoe UI", Font.BOLD, 20);

    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel centerPanel;
    private JPanel formPanel;

    private JLabel logoLabel;
    private JButton backButton;

    private JLabel formTitleLabel;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton submitButton;

    public Login() {
        setTitle("Sistema Bancario - Iniciar Sesión");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setResizable(false);
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(backgroundColor);

        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(whiteColor);
        
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorderColor, 1),
                BorderFactory.createEmptyBorder(30, 40, 40, 40)
        ));

        logoLabel = new JLabel("\uD83C\uDFE6 Sistema Bancario");
        logoLabel.setFont(logoFont);
        logoLabel.setForeground(whiteColor);

        backButton = new JButton("Volver");
        backButton.setFont(buttonFont);
        backButton.setBackground(primaryColor);
        backButton.setForeground(whiteColor);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                Home home = new Home();
                home.setVisible(true);
            }
        });

        formTitleLabel = new JLabel("Iniciar Sesión");
        formTitleLabel.setFont(titleFont);
        formTitleLabel.setForeground(primaryColor);
        formTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        emailLabel = new JLabel("Correo Electrónico");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(primaryColor);

        emailField = new JTextField(20);
        emailField.setFont(inputFont);
        
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorderColor, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(primaryColor);

        passwordField = new JPasswordField(20);
        passwordField.setFont(inputFont);
        
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(inputBorderColor, 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        submitButton = new JButton("Ingresar");
        submitButton.setFont(buttonFont);
        submitButton.setBackground(actionAccent);
        submitButton.setForeground(whiteColor);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login();
            }
        });
        
        headerPanel.add(logoLabel, BorderLayout.WEST);
        headerPanel.add(backButton, BorderLayout.EAST);

        GridBagConstraints formGbc1 = new GridBagConstraints();
        formGbc1.gridx = 0;
        formGbc1.gridy = 0;
        formGbc1.fill = GridBagConstraints.HORIZONTAL;
        formGbc1.insets = new Insets(0, 0, 30, 0);
        formPanel.add(formTitleLabel, formGbc1);

        GridBagConstraints formGbc2 = new GridBagConstraints();
        formGbc2.gridx = 0;
        formGbc2.gridy = 1;
        formGbc2.fill = GridBagConstraints.HORIZONTAL;
        formGbc2.insets = new Insets(0, 0, 5, 0);
        formPanel.add(emailLabel, formGbc2);

        GridBagConstraints formGbc3 = new GridBagConstraints();
        formGbc3.gridx = 0;
        formGbc3.gridy = 2;
        formGbc3.fill = GridBagConstraints.HORIZONTAL;
        formGbc3.insets = new Insets(0, 0, 20, 0);
        formPanel.add(emailField, formGbc3);

        GridBagConstraints formGbc4 = new GridBagConstraints();
        formGbc4.gridx = 0;
        formGbc4.gridy = 3;
        formGbc4.fill = GridBagConstraints.HORIZONTAL;
        formGbc4.insets = new Insets(0, 0, 5, 0);
        formPanel.add(passwordLabel, formGbc4);

        GridBagConstraints formGbc5 = new GridBagConstraints();
        formGbc5.gridx = 0;
        formGbc5.gridy = 4;
        formGbc5.fill = GridBagConstraints.HORIZONTAL;
        formGbc5.insets = new Insets(0, 0, 30, 0);
        formPanel.add(passwordField, formGbc5);

        GridBagConstraints formGbc6 = new GridBagConstraints();
        formGbc6.gridx = 0;
        formGbc6.gridy = 5;
        formGbc6.fill = GridBagConstraints.HORIZONTAL;
        formGbc6.insets = new Insets(0, 0, 0, 0);
        formPanel.add(submitButton, formGbc6);

        GridBagConstraints centerGbc = new GridBagConstraints();
        centerGbc.gridx = 0;
        centerGbc.gridy = 0;
        centerPanel.add(formPanel, centerGbc);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    public JButton getBackButton() { return backButton; }
    public JButton getSubmitButton() { return submitButton; }
    public String getEmail() { return emailField.getText(); }
    public String getPassword() { return new String(passwordField.getPassword()); }
    
    private void login() {
        AuthController authController = new AuthController(this);
        LoginDTO loginDTO = new LoginDTO(getEmail(), getPassword());

        try {
            authController.login(loginDTO);
            JOptionPane.showMessageDialog(this, "¡Bienvenido al Sistema Bancario!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        } 
        catch (ValidationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
        catch (InvalidCredentialsException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        }
    } 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
