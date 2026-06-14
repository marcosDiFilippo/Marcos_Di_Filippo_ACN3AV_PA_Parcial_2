package services;

import dao.UserDAO;
import dto.Auth.LoginDTO;
import errors.InvalidCredentialsException;
import errors.UserNotFoundException;
import model.User;
import validators.AuthValidator;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public User login(LoginDTO loginDTO) throws Exception {
        AuthValidator.validateLogin(loginDTO);

        try {
            return userDAO.findByCredentials(loginDTO.email, loginDTO.password);
        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException("El usuario o la contraseña son incorrectos.");
        }
    }
}
