/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 05:43:06 PM
 */
package edu.unc.auth_eventos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Clase que representa el DTO de petición de Rol.
 * <p>
 * Esta clase es un DTO que se utiliza para recibir información sobre un rol.
 * La anotación {@code @Data} es una anotación de Lombok que genera automáticamente getters, setters, toString, equals y hashCode.
 */
@Data
public class RolDTO {
    /**
     * El identificador único del rol.
     */
    private Long idRol;

    /**
     * El nombre del rol.
     */
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 a 30 caracteres.")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚ\\s]*$", message = "El nombre solo puede contener letras")
    private String nombre;
}
