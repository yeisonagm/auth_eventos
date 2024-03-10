/**
 * @file: Usuario.java
 * @author: (c)2024 Aldo Pereyra (AldoPM)
 * @created: Mar 09, 2024 07:00:06 PM
 */
package edu.unc.auth_eventos.repository;

import edu.unc.auth_eventos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Usuario en la base de datos.
 * Proporciona operaciones de consulta específicas para la entidad Usuario.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por email.
     *
     * @param email El email del usuario.
     * @return El usuario con el email proporcionado.
     */
    Usuario findByEmail(String email);
}