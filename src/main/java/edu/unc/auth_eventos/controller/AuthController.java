/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 09, 2024 06:43:06 PM
 */
package edu.unc.auth_eventos.controller;

import edu.unc.auth_eventos.dto.LoginRequest;
import edu.unc.auth_eventos.entity.Usuario;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import edu.unc.auth_eventos.service.AuthService;
import edu.unc.auth_eventos.util.ApiResponse;
import edu.unc.auth_eventos.util.EntityValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que gestiona las operaciones de autenticación.
 * <p>Se mapea a la ruta '/auth' y espera que todas las solicitudes incluyan el encabezado 'Api-Version=1'.
 * Las operaciones incluyen iniciar sesión y registrar un nuevo usuario.</p>
 */
@RestController
@RequestMapping(value = "/auth", headers = "Api-Version=1")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Inicia sesión en el sistema.
     *
     * @param userLogin datos de inicio de sesión.
     * @return token de autenticación.
     * @throws AccessDeniedException si las credenciales son incorrectas.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest userLogin, BindingResult result) throws AccessDeniedException {
        if (result.hasErrors()) return new EntityValidator().validate(result);

        Usuario usuario = modelMapper.map(userLogin, Usuario.class);
        String token = authService.login(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                true,
                "Token generado correctamente",
                token));
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param userRegister datos del nuevo usuario.
     * @return el usuario registrado.
     * @throws IllegalOperationException si el usuario ya existe.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid LoginRequest userRegister, BindingResult result) throws IllegalOperationException {
        if (result.hasErrors()) return new EntityValidator().validate(result);

        Usuario usuario = modelMapper.map(userRegister, Usuario.class);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(
                true,
                "Usuario registrado correctamente",
                authService.register(usuario)));
    }
}
