/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 10:43:47 PM
 */
package edu.unc.auth_eventos.service;

import edu.unc.auth_eventos.entity.Rol;
import edu.unc.auth_eventos.entity.Usuario;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import edu.unc.auth_eventos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de los servicios relacionados con la entidad Usuario.
 * Proporciona métodos para realizar operaciones CRUD (Create, Read, Update, Delete) en la entidad Usuario.
 */
@Service
public class UsuarioServiceImp implements UsuarioService {
    /**
     * Repositorio para la entidad Usuario.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolService rolService;

    /**
     * Obtiene todos los usuarios en el sistema.
     *
     * @return Lista de usuarios.
     */
    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @return El usuario con el identificador proporcionado.
     * @throws EntityNotFoundException Si no se encuentra ningún usuario con el identificador especificado.
     */
    @Override
    public Usuario getById(Long id) throws EntityNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("El usuario con el Id proporcionado no se encontró.");
        }
        return usuarioOpt.get();
    }

    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuario El usuario a guardar.
     * @return El usuario guardado.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante el proceso de guardado del usuario.
     */
    @Override
    public Usuario save(Usuario usuario) throws IllegalOperationException {
        Rol rol = rolService.getById(usuario.getRol().getIdRol());
        if (rol == null) {
            throw new IllegalOperationException("El rol proporcionado no existe.");
        }
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalOperationException("El correo electrónico proporcionado ya está en uso.");
        }
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    /**
     * Actualiza un usuario existente en el sistema.
     *
     * @param id      Identificador único del usuario a actualizar.
     * @param usuario El objeto Usuario con los nuevos datos del usuario.
     * @return El usuario actualizado.
     * @throws EntityNotFoundException   Si no se encuentra ningún usuario con el identificador especificado.
     * @throws IllegalOperationException Si ocurre una operación ilegal durante el proceso de actualización del usuario.
     */
    @Override
    public Usuario update(Long id, Usuario usuario) throws EntityNotFoundException, IllegalOperationException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new EntityNotFoundException("El usuario con el Id proporcionado no se encontró.");
        }
//        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
//            throw new IllegalOperationException("El correo electrónico proporcionado ya está en uso.");
//        }
        Rol rol = rolService.getById(usuario.getRol().getIdRol());
        if (rol == null) {
            throw new IllegalOperationException("El rol proporcionado no existe.");
        }
        usuario.setRol(rol);
        usuario.setIdUsuario(id);
        return usuarioRepository.save(usuario);
    }

    /**
     * Elimina un usuario del sistema por su identificador único.
     *
     * @param id Identificador único del usuario a eliminar.
     * @throws EntityNotFoundException Si no se encuentra ningún usuario con el identificador especificado.
     */
    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario con el Id proporcionado no se encontró."));
        usuarioRepository.delete(usuario);
    }
}
