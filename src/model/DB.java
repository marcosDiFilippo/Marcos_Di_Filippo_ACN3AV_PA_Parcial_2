package model;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    
    private static DB instance;
    private Connection connection;
    
    private static final String URL = "jdbc:mysql://localhost:3306/bank_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    private DB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MySQL (com.mysql.cj.jdbc.Driver) no encontrado.");
            JOptionPane.showMessageDialog(null, "Error: Driver de MySQL no encontrado.\n" + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            System.err.println("Error: No se pudo conectar a la base de datos MySQL.");
            JOptionPane.showMessageDialog(null, "Error: No se pudo conectar a la base de datos MySQL.\n" + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.err.println("Error: Ha ocurrido un error inesperado.");
            JOptionPane.showMessageDialog(null, "Error inesperado en la base de datos:\n" + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Connection getConnection() {
        if (instance == null) {
            instance = new DB();
        }
        return instance.connection;
    }
}
