/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 01:13:24 Am
 */
package edu.unc.auth_eventos.controller;

import edu.unc.auth_eventos.dto.RolDTO;
import edu.unc.auth_eventos.entity.Rol;
import edu.unc.auth_eventos.exception.EntityNotFoundException;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import edu.unc.auth_eventos.service.RolService;
import edu.unc.auth_eventos.util.ApiResponse;
import edu.unc.auth_eventos.util.EntityValidator;
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
 * Controlador REST que gestiona las operaciones CRUD para 'Roles'.
 * <p>Se mapea a la ruta '/auth' y espera que todas las solicitudes incluyan el encabezado 'Api-Version=1'.
 * Las operaciones incluyen obtener todos los roles, obtener un rol por su ID,
 * guardar un nuevo rol, actualizar un rol existente y eliminar un rol.</p>
 */
@RestController
@RequestMapping(value = "/roles", headers = "Api-Version=1")
public class RolController {
    @Autowired
    private RolService rolService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Obtiene todos los roles en el sistema.
     *
     * @return Lista de roles.
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Rol> roles = rolService.getAll();
        List<RolDTO> rolDTOs = roles.stream()
                .map(rol -> modelMapper.map(rol, RolDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Lista de roles",
                rolDTOs));
    }

    /**
     * Obtiene un rol por su identificador único.
     *
     * @param id Identificador único del rol.
     * @return El rol encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Rol rol = rolService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Rol encontrado",
                modelMapper.map(rol, RolDTO.class)));
    }

    /**
     * Guarda un nuevo rol en el sistema.
     *
     * @param rolDTO El rol a guardar.
     * @return El rol guardado.
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid RolDTO rolDTO, BindingResult result) throws IllegalOperationException {
        if (result.hasErrors()) return new EntityValidator().validate(result);
        Rol rol = modelMapper.map(rolDTO, Rol.class);
        Rol rolSaved = rolService.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                true,
                "Rol guardado",
                modelMapper.map(rolSaved, RolDTO.class)));
    }

    /**
     * Actualiza un rol existente en el sistema.
     *
     * @param id     Identificador único del rol a actualizar.
     * @param rolDTO El objeto Rol con los nuevos datos del rol.
     * @return El rol actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid RolDTO rolDTO, BindingResult result)
            throws EntityNotFoundException, IllegalOperationException {
        if (result.hasErrors()) return new EntityValidator().validate(result);

        Rol rol = modelMapper.map(rolDTO, Rol.class);
        rol.setIdRol(id);
        Rol rolUpdated = rolService.save(rol);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                true,
                "Rol actualizado",
                modelMapper.map(rolUpdated, RolDTO.class)));
    }
}
