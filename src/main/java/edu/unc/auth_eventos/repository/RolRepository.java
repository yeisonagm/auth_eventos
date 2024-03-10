/**
 * @file: Usuario.java
 * @author: (c)2024 Aldo Pereyra (AldoPM)
 * @created: Mar 09, 2024 07:00:06 PM
 */
package edu.unc.auth_eventos.repository;

import edu.unc.auth_eventos.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Rol en la base de datos.
 * Proporciona operaciones de consulta específicas para la entidad Rol.
 */
public interface RolRepository extends JpaRepository<Rol,Long> {

    /**
     * Busca un rol por nombre.
     *
     * @param nombre El nombre del rol.
     * @return El rol con el nombre proporcionado.
     */
    Rol findByNombre(String nombre);
}
