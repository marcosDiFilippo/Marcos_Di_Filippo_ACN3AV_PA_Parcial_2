package validators;

import dto.Auth.LoginDTO;
import errors.ValidationException;

public class AuthValidator {
    
    public static void validateLogin(LoginDTO dto) throws ValidationException {
        if (dto.email == null || dto.email.trim().isEmpty()) {
            throw new ValidationException("El correo electrónico es obligatorio.");
        }
        if (!dto.email.contains("@")) {
            throw new ValidationException("Por favor, ingrese un correo electrónico válido.");
        }
        if (dto.password == null || dto.password.trim().isEmpty()) {
            throw new ValidationException("La contraseña es obligatoria.");
        }
    }
}
