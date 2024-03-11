/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 09:13:01 AM
 */
package edu.unc.auth_eventos.service;

import edu.unc.auth_eventos.config.CustomUserDetails;
import edu.unc.auth_eventos.dto.LoginResponse;
import edu.unc.auth_eventos.entity.Rol;
import edu.unc.auth_eventos.entity.Usuario;
import edu.unc.auth_eventos.exception.IllegalOperationException;
import edu.unc.auth_eventos.repository.RolRepository;
import edu.unc.auth_eventos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Clase que representa el servicio de autenticación.
 * <p>
 * Esta clase es un servicio que se utiliza para definir los métodos de autenticación.
 */
@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Método para iniciar sesión.
     *
     * @param usuario con la información de login.
     * @return El token de autenticación.
     */
    @Override
    public LoginResponse login(Usuario usuario) throws BadCredentialsException {
        Usuario usuarioDB = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioDB == null || !passwordEncoder.matches(usuario.getPassword(), usuarioDB.getPassword())) {
            throw new BadCredentialsException("La credenciales son incorrectas.");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
        return new LoginResponse(
                usuario.getEmail(),
                usuarioDB.getRol().getNombre(),
                jwtService.getToken(new CustomUserDetails(usuarioDB)));
    }

    /**
     * Método para registrar un nuevo usuario.
     *
     * @param usuario con la información de registro.
     * @return El token de autenticación.
     */
    @Override
    public LoginResponse register(Usuario usuario) throws IllegalOperationException {
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalOperationException("El email ya está registrado.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Rol rol = rolRepository.findByNombre("Cliente");
        if (rol == null) {
            throw new IllegalOperationException("No se puede crear un 'cliente', debido a que el rol no existe.");
        }

        usuario.setRol(rol);
        usuarioRepository.save(usuario);
        return new LoginResponse(
                usuario.getEmail(),
                usuario.getRol().getNombre(),
                jwtService.getToken(new CustomUserDetails(usuario)));
    }

    /**
     * Método para buscar un usuario por email.
     *
     * @param email Email del usuario.
     * @return El usuario.
     */
    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
