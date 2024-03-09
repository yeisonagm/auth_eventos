/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 06:23:06 PM
 */
package edu.unc.auth_eventos.dto;

import lombok.Data;

/**
 * Clase que representa el DTO de respuesta de Usuario.
 * <p>
 * Esta clase es un DTO que se utiliza para devolver información sobre un usuario.
 * La anotación {@code @Data} es una anotación de Lombok que genera automáticamente getters, setters, toString, equals y hashCode.
 */
@Data
public class UsuarioResponseDTO {
    /**
     * El identificador único del usuario.
     */
    private Long idUsuario;

    /**
     * El email del usuario.
     */
    private String email;

    /**
     * El rol del usuario.
     */
    private RolDTO rol;
}
