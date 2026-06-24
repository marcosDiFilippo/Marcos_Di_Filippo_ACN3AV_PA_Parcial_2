package controllers;

import javax.swing.JFrame;

import dto.LoginDTO;
import model.User;
import session.UserSession;
import views.DashboardEmployee;
import views.DashboardUser;
import views.Login;
import services.AuthService;

public class AuthController {

    private AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    public void login(LoginDTO loginDTO) throws Exception {
        User user = authService.login(loginDTO);
        new UserSession(user);

        if (user.isEmployee()) {
            DashboardEmployee dashboardEmployee = new DashboardEmployee();
            dashboardEmployee.setVisible(true);
            return;
        }
        
        DashboardUser dashboardUser = new DashboardUser();
        dashboardUser.setVisible(true);
    }

    public void logout (JFrame parentView) {
        UserSession.closeSession();
        parentView.dispose();
        
        Login login = new Login();
        login.setVisible(true);
    }
}
