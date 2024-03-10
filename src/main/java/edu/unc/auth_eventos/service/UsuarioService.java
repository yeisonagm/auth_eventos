/**
 * @file: UsuarioService.java
 * @author: (c)2024 Aldo Pereyra (AldoPM)
 * @created: Mar 09, 2024 07:07:06 PM
 */
package edu.unc.auth_eventos.service;

import edu.unc.auth_eventos.entity.Usuario;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

/**
 * Interfaz que define los servicios relacionados con la entidad Usuario.
 * Proporciona métodos para realizar operaciones CRUD (Create, Read, Update, Delete) en la entidad Usuario.
 */
public interface UsuarioService {

    /**
     * Obtiene todos los usuarios en el sistema.
     *
     * @return Lista de usuarios.
     */
    List<Usuario> getAll();

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @return El usuario con el identificador proporcionado.
     * @throws EntityNotFoundException Si no se encuentra ningún usuario con el identificador especificado.
     */
    Usuario getById(Long id) throws EntityNotFoundException;

    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuario El usuario a guardar.
     * @return El usuario guardado.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante el proceso de guardado del usuario.
     */
    Usuario save(Usuario usuario) throws IllegalOperationException;

    /**
     * Actualiza un usuario existente en el sistema.
     *
     * @param id      Identificador único del usuario a actualizar.
     * @param usuario El objeto Usuario con los nuevos datos del usuario.
     * @return El usuario actualizado.
     * @throws EntityNotFoundException Si no se encuentra ningún usuario con el identificador especificado.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante el proceso de actualización del usuario.
     */
    Usuario update(Long id, Usuario usuario) throws EntityNotFoundException, IllegalOperationException;

    /**
     * Elimina un usuario del sistema por su identificador único.
     *
     * @param id Identificador único del usuario a eliminar.
     * @throws EntityNotFoundException Si no se encuentra ningún usuario con el identificador especificado.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante el proceso de eliminación del usuario.
     */
    void delete(Long id) throws EntityNotFoundException, IllegalOperationException;
}
