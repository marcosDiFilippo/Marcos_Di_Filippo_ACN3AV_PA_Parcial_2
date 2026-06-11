package model;

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
            
            System.out.println("Conexión a la base de datos establecida exitosamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver de MySQL (com.mysql.cj.jdbc.Driver) no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: No se pudo conectar a la base de datos MySQL.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: Ha ocurrido un error inesperado.");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
        if (instance == null) {
            instance = new DB();
        }
        return instance.connection;
    }
}
