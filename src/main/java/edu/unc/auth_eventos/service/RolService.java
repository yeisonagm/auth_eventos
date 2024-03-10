/**
 * @file: RolService.java
 * @author: (c)2024 Aldo Pereyra (AldoPM)
 * @created: Mar 09, 2024 07:07:06 PM
 */
package edu.unc.auth_eventos.service;

import edu.unc.auth_eventos.entity.Rol;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

/**
 * Interfaz que define los servicios relacionados con la entidad Rol.
 * Proporciona métodos para realizar operaciones CRUD (Create, Read, Update, Delete) en la entidad Rol.
 */
public interface RolService {

    /**
     * Obtiene todos los roles en el sistema.
     *
     * @return Lista de roles.
     */
    List<Rol> getAll();

    /**
     * Obtiene un rol por su identificador único.
     *
     * @param id Identificador único del rol.
     * @return El rol con el identificador proporcionado.
     * @throws EntityNotFoundException Si no se encuentra ningún rol con el identificador especificado.
     */
    Rol getById(Long id) throws EntityNotFoundException;

    /**
     * Guarda un nuevo rol en el sistema.
     *
     * @param rol El rol a guardar.
     * @return El rol guardado.
     */
    Rol save(Rol rol);//Faltaba agregar el illegalOperationException

    /**
     * Actualiza un rol existente en el sistema.
     *
     * @param id  Identificador único del rol a actualizar.
     * @param rol El objeto Rol con los nuevos datos del rol.
     * @return El rol actualizado.
     * @throws EntityNotFoundException  Si no se encuentra ningún rol con el identificador especificado.
     */
    Rol update(Long id, Rol rol) throws EntityNotFoundException;


}
