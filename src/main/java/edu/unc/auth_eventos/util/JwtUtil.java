/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 09:13:01 AM
 */
package edu.unc.auth_eventos.util;

/**
 * Clase que representa el utilitario de JWT.
 * <p>
 * Esta clase es un utilitario que se utiliza para definir las constantes de JWT.
 */
public class JwtUtil {
    /**
     * La clave secreta para firmar el token.
     */
    public static final String SECRET_KEY = "UNacionalCajamarcaSistemasTrabajoFinalGrupo05";
    /**
     * El tiempo de expiración del token de autenticación.
     */
    public static final long EXPIRE_ACCESS_TOKEN = 600000; // 10 min
}