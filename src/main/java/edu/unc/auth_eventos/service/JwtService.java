/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 03:43:47 PM
 */
package edu.unc.auth_eventos.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

/**
 * Clase que representa el servicio de autenticación.
 * <p>
 * Esta clase es un servicio que se utiliza para definir los métodos de autenticación.
 */
public interface JwtService {
    /**
     * Método para generar un token de autenticación.
     *
     * @param user con la información del usuario.
     * @return El token de autenticación.
     */
    String getToken(UserDetails user);

    /**
     * Método para obtener el nombre de usuario a partir de un token de autenticación.
     *
     * @param token El token de autenticación.
     * @return El nombre de usuario.
     */
    String getUsernameFromToken(String token);

    /**
     * Método para validar un token de autenticación.
     *
     * @param token       El token de autenticación.
     * @param userDetails Los detalles del usuario.
     * @return true si el token es válido, false de lo contrario.
     */

    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Método para obtener el claim de un token de autenticación.
     *
     * @param token          El token de autenticación.
     * @param claimsResolver El resolver de claims.
     * @return El claim del token.
     */
    <T> T getClaim(String token, Function<Claims, T> claimsResolver);
}
