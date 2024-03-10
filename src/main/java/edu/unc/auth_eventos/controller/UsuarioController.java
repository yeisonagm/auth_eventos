/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 11:43:47 PM
 */
package edu.unc.auth_eventos.controller;

import edu.unc.auth_eventos.dto.UsuarioRequestDTO;
import edu.unc.auth_eventos.dto.UsuarioResponseDTO;
import edu.unc.auth_eventos.entity.Usuario;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import edu.unc.auth_eventos.service.UsuarioService;
import edu.unc.auth_eventos.util.ApiResponse;
import edu.unc.auth_eventos.util.EntityValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST que gestiona las operaciones CRUD para 'Usuarios'.
 * <p>Se mapea a la ruta '/auth' y espera que todas las solicitudes incluyan el encabezado 'Api-Version=1'.
 * Las operaciones incluyen obtener todos los usuarios, obtener un usuario por su ID,
 * guardar un nuevo usuario, actualizar un usuario existente y eliminar un usuario.</p>
 */
@RestController
@RequestMapping(value = "/usuarios", headers = "Api-Version=1")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtiene todos los usuarios en el sistema.
     *
     * @return Lista de usuarios.
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Usuario> usuarios = usuarioService.getAll();
        List<UsuarioResponseDTO> usuarioDTOs = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Lista de usuarios",
                usuarioDTOs));
    }

    /**
     * Obtiene un usuario por su identificador único.
     *
     * @param id Identificador único del usuario.
     * @return El usuario con el identificador proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(Long id) {
        Usuario usuario = usuarioService.getById(id);
        UsuarioRequestDTO usuarioDTO = modelMapper.map(usuario, UsuarioRequestDTO.class);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Usuario encontrado",
                usuarioDTO));
    }

    /**
     * Guarda un nuevo usuario en el sistema.
     *
     * @param usuarioDTO El usuario a guardar.
     * @return El usuario guardado.
     */
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid UsuarioRequestDTO usuarioDTO, BindingResult result) throws IllegalOperationException {
        if (result.hasErrors()) return new EntityValidator().validate(result);

        Usuario usuario = usuarioService.save(modelMapper.map(usuarioDTO, Usuario.class));
        ApiResponse<UsuarioResponseDTO> response = new ApiResponse<>(
                true,
                "Usuario guardado",
                modelMapper.map(usuario, UsuarioResponseDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Actualiza un usuario existente en el sistema.
     *
     * @param id      Identificador único del usuario a actualizar.
     * @param usuarioDTO El objeto Usuario con los nuevos datos del usuario.
     * @return El usuario actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO usuarioDTO, BindingResult result)
            throws EntityNotFoundException, IllegalOperationException {
        if (result.hasErrors()) return new EntityValidator().validate(result);

        Usuario usuario = usuarioService.update(id, modelMapper.map(usuarioDTO, Usuario.class));
        ApiResponse<UsuarioResponseDTO> response = new ApiResponse<>(
                true,
                "Usuario actualizado",
                modelMapper.map(usuario, UsuarioResponseDTO.class));
        return ResponseEntity.ok(response);
    }

    /**
     * Elimina un usuario existente en el sistema.
     *
     * @param id Identificador único del usuario a eliminar.
     * @return Respuesta indicando la operación con éxito.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "Usuario eliminado", null));
    }
}
