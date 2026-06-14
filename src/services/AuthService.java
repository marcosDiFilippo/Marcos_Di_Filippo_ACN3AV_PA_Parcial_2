package services;

import dao.UserDAO;
import dto.Auth.LoginDTO;
import errors.InvalidCredentialsException;
import helpers.PasswordHasher;
import model.User;
import validators.AuthValidator;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public User login(LoginDTO loginDTO) throws Exception {
        AuthValidator.validateLogin(loginDTO);

        User user = userDAO.findByEmail(loginDTO.email);
        if (user == null) {
            throw new InvalidCredentialsException("El usuario o la contraseña son incorrectos.");
        }
        
        String hashedInput = PasswordHasher.hashSHA256(loginDTO.password);
        if (!user.getPassword().equals(hashedInput)) {
            throw new InvalidCredentialsException("El usuario o la contraseña son incorrectos.");
        }
        
        return user;
    }
}
