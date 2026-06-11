package controllers;

import dao.UserDAO;
import dto.Auth.LoginDTO;
import model.User;
import validators.AuthValidator;
import views.DashboardEmployee;
import views.DashboardUser;
import errors.InvalidCredentialsException;
import helpers.PasswordHasher;

public class AuthController {

    public void login(LoginDTO loginDTO) throws Exception {
        
        AuthValidator.validateLogin(loginDTO);
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByEmail(loginDTO.email);
        
        if (user == null) {
            throw new InvalidCredentialsException("El usuario o la contraseña son incorrectos.");
        }
        
        String hashedInput = PasswordHasher.hashSHA256(loginDTO.password);

        if (!user.getPassword().equals(hashedInput)) {
            throw new InvalidCredentialsException("El usuario o la contraseña son incorrectos.");
        }

        
        if (user.isEmployee()) {
            DashboardEmployee dashboardEmployee = new DashboardEmployee();
            dashboardEmployee.setVisible(true);
            return;
        }
        DashboardUser dashboardUser = new DashboardUser();
        dashboardUser.setVisible(true);
        
    }
}
