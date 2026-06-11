package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import errors.UserNotFoundException;
import model.BankAccount;
import model.BankEmployee;
import model.DB;
import model.User;

public class UserDAO {

    private BankAccountDAO bankAccountDAO;
    private BankEmployeeDAO bankEmployeeDAO;

    public UserDAO() {
        this.bankAccountDAO = new BankAccountDAO();
        this.bankEmployeeDAO = new BankEmployeeDAO();
    }
    
    public User findByEmail(String email) throws SQLException, UserNotFoundException {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            Connection conn = DB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (!rs.next()) {
                throw new UserNotFoundException("Usuario no encontrado");
            }

            User user = getTypeUserById(rs.getLong("id"));
            
            user.setUserId(rs.getLong("id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            
            return user;
        } 
        catch (UserNotFoundException e) {
            throw e;
        }
        catch (SQLException e) {
            throw e;
        }
        catch (Exception e) {
            throw e;
        }
    }

    private User getTypeUserById (long id) throws SQLException, UserNotFoundException {
        User user = this.bankAccountDAO.findByUserId(id);
    
        if (user != null) {
            return user;
        }

        user = this.bankEmployeeDAO.findById(id);
        
        if (user != null) {
            return user;
        }

        throw new UserNotFoundException("Usuario no encontrado");
    }
}
