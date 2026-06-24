package services;

import dao.UserDAO;
import dto.LoginDTO;
import errors.InvalidCredentialsException;
import errors.UserNotFoundException;
import errors.ValidationException;
import model.User;

public class AuthService {

    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public User login(LoginDTO loginDTO) throws Exception {

        String email = loginDTO.email;
        String password = loginDTO.password;

        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("El correo electrónico es obligatorio.");
        }
        if (!email.contains("@")) {
            throw new ValidationException("Por favor, ingrese un correo electrónico válido.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("La contraseña es obligatoria.");
        }

        try {
            return userDAO.findByCredentials(email, password);
        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException("El usuario o la contraseña son incorrectos.");
        }
    }
}
