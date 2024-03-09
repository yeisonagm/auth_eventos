/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 05:43:06 PM
 */
package edu.unc.auth_eventos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa la entidad Rol en la base de datos.
 * <p>
 * Esta clase es una entidad JPA, lo que significa que es una clase que se mapea a una tabla en la base de datos.
 * La anotación {@code @Entity} indica que esta clase es una entidad.
 * <p>
 * La anotación {@code @Data} es una anotación de Lombok que genera automáticamente getters, setters, toString, equals y hashCode.
 */
@Entity
@Data
public class Rol {
    /**
     * El identificador único del rol.
     * La anotación {@code @Id} indica que este campo es la clave primaria de la tabla Rol.
     * La anotación {@code @GeneratedValue} indica que el valor de este campo se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    /**
     * El nombre del rol.
     */
    private String nombre;

    /**
     * La lista de usuarios que tienen este rol.
     */
    @OneToMany(mappedBy = "rol")
    private Set<Usuario> usuarios = new HashSet<>();
}
