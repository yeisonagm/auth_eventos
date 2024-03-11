/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 10:12:01 AM
 */
package edu.unc.auth_eventos.service;

import edu.unc.auth_eventos.dto.LoginResponse;
import edu.unc.auth_eventos.entity.Usuario;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * Interfaz que define los métodos de autenticación.
 */
public interface AuthService {
    /**
     * Método para iniciar sesión.
     *
     * @param usuario con la información de login.
     * @return El token de autenticación.
     */
    LoginResponse login(Usuario usuario) throws BadCredentialsException;

    /**
     * Método para registrar un nuevo usuario.
     *
     * @param usuario con la información de registro.
     * @return El token de autenticación.
     */
    LoginResponse register(Usuario usuario) throws IllegalOperationException;

    /**
     * Método para buscar un usuario por email.
     *
     * @param email Email del usuario.
     * @return El usuario.
     */
    Usuario findByEmail(String email);
}
