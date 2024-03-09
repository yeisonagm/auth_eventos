/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 05:43:06 PM
 */
package edu.unc.auth_eventos.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Clase que representa la entidad Usuario en la base de datos.
 * <p>
 * Esta clase es una entidad JPA, lo que significa que es una clase que se mapea a una tabla en la base de datos.
 * La anotación {@code @Entity} indica que esta clase es una entidad.
 * <p>
 * La anotación {@code @Data} es una anotación de Lombok que genera automáticamente getters, setters, toString, equals y hashCode.
 */
@Entity
@Data
public class Usuario {
    /**
     * El identificador único del usuario.
     * <p>
     * La anotación {@code @Id} indica que este campo es la clave primaria de la tabla.
     * La anotación {@code @GeneratedValue} indica que el valor de este campo se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    /**
     * El email del usuario.
     */
    private String email;

    /**
     * La contraseña del usuario.
     */
    private String password;

    /**
     * El rol del usuario.
     * <p>
     * La anotación {@code @ManyToOne} indica que esta relación es de muchos a uno.
     * La anotación {@code @JoinColumn} indica que el campo que se utilizará para la relación es el campo id_rol.
     */
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}
