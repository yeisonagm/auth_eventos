/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 10:23:59 PM
 */
package edu.unc.auth_eventos.service;

import edu.unc.auth_eventos.entity.Rol;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import edu.unc.auth_eventos.repository.RolRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de los servicios relacionados con la entidad Rol.
 * Proporciona métodos para realizar operaciones CRUD (Create, Read, Update, Delete) en la entidad Rol.
 */
@Service
public class RolServiceImp implements RolService {
    /**
     * Repositorio para la entidad Rol.
     */
    @Autowired
    private RolRepository rolRepository;

    /**
     * Obtiene todos los roles en el sistema.
     *
     * @return Lista de roles.
     */
    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    /**
     * Obtiene un rol por su identificador único.
     *
     * @param id Identificador único del rol.
     * @return El rol con el identificador proporcionado.
     * @throws EntityNotFoundException Si no se encuentra ningún rol con el identificador especificado.
     */
    @Override
    public Rol getById(Long id) throws EntityNotFoundException {
        Optional<Rol> rolOpt = rolRepository.findById(id);
        if (rolOpt.isEmpty()) {
            throw new EntityNotFoundException("El rol con el Id proporcionado no se encontró.");
        }
        return rolOpt.get();
    }

    /**
     * Guarda un nuevo rol en el sistema.
     *
     * @param rol El rol a guardar.
     * @return El rol guardado.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante el proceso de guardado del rol.
     */
    @Override
    public Rol save(Rol rol) throws IllegalOperationException {
        if (rolRepository.findByNombre(rol.getNombre()) != null) {
            throw new IllegalOperationException("Ya existe un rol con el nombre proporcionado.");
        }
        return rolRepository.save(rol);
    }

    /**
     * Actualiza un rol existente en el sistema.
     *
     * @param id  Identificador único del rol a actualizar.
     * @param rol El objeto Rol con los nuevos datos del rol.
     * @return El rol actualizado.
     * @throws EntityNotFoundException   Si no se encuentra ningún rol con el identificador especificado.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante el proceso de actualización del rol.
     */
    @Override
    public Rol update(Long id, Rol rol) throws EntityNotFoundException, IllegalOperationException {
        Optional<Rol> rolOpt = rolRepository.findById(id);
        if (rolOpt.isEmpty()) {
            throw new EntityNotFoundException("El rol con el Id proporcionado no se encontró.");
        }
        if (rolRepository.findByNombre(rol.getNombre()) != null) {
            throw new IllegalOperationException("Ya existe un rol con el nombre proporcionado.");
        }
        rol.setIdRol(id);
        return rolRepository.save(rol);
    }
}
