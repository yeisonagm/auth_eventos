/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 09:13:01 AM
 */
package edu.unc.auth_eventos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa el DTO de respuesta de login de Usuario.
 * <p>
 * Esta clase es un DTO que se utiliza para enviar información de login de un usuario.
 */
@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {
    /**
     * El email del usuario.
     */
    private String email;
    /**
     * El rol del usuario.
     */
    private String rol;
    /**
     * El token del usuario.
     */
    private String token;
}
