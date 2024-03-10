/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 06:43:06 PM
 */
package edu.unc.auth_eventos.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Clase que representa el DTO de petición de Usuario.
 * <p>
 * Esta clase es un DTO que se utiliza para recibir información sobre un usuario.
 * La anotación {@code @Data} es una anotación de Lombok que genera automáticamente getters, setters, toString, equals y hashCode.
 */
@Data
public class UsuarioRequestDTO {
    /**
     * El email del usuario.
     */
    @NotBlank(message = "El Nombre no puede estar vacío.")
    @Column(unique = true)
    @Size(max = 30, message = "El email debe tener menos de 30 caracteres.")
    @Email(message = "El email debe ser válido.")
    private String email;

    /**
     * La contraseña del usuario.
     */
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 6, max = 24, message = "La contraseña debe tener entre 6 a 24 caracteres.")
    private String password;

    /**
     * El rol del usuario.
     */
    @NotNull(message = "El rol no puede estar vacío.")
    private RolDTO rol;
}
