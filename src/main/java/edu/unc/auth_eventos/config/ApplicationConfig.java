/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 08:13:01 PM
 */
package edu.unc.auth_eventos.config;

import edu.unc.auth_eventos.entity.Usuario;
import edu.unc.auth_eventos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Clase de configuración de la aplicación.
 *
 * Esta clase se utiliza para configurar varios beans que se utilizan en la aplicación.
 * Los beans configurados incluyen ModelMapper, AuthenticationManager, AuthenticationProvider,
 * PasswordEncoder y UserDetailsService.
 *
 * @Configuration: Esta anotación indica que la clase tiene métodos @Bean y puede ser
 * procesada por el contenedor de Spring para generar definiciones de beans y solicitudes de servicio.
 *
 * @RequiredArgsConstructor: Esta anotación de Lombok genera un constructor con 1 parámetro para cada campo
 * que requiere una inicialización especial. Todos los campos no inicializados obtienen un parámetro.
 *
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Autowired
    private UsuarioRepository userRepository;

    /**
     * {@code ModelMapper} es una biblioteca que simplifica el mapeo de objetos en Java.
     * Se utiliza a menudo para convertir entre objetos de dominio y DTOs.
     * <p>
     * La anotación {@code @Bean} indica que este método produce un bean que debe ser gestionado por el contenedor de Spring.
     *
     * @return una nueva instancia de {@code ModelMapper}.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Crea una nueva instancia de AuthenticationManager.
     * AuthenticationManager es una interfaz que se utiliza para autenticar a un usuario.
     *
     * @param config la configuración de autenticación.
     * @return una nueva instancia de AuthenticationManager.
     * @throws Exception si ocurre un error al crear el AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Crea una nueva instancia de AuthenticationProvider.
     * AuthenticationProvider es una interfaz que se utiliza para autenticar a un usuario.
     *
     * @return una nueva instancia de AuthenticationProvider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Crea una nueva instancia de BCryptPasswordEncoder.
     * BCryptPasswordEncoder es un codificador de contraseñas que utiliza el algoritmo de hash BCrypt.
     *
     * @return una nueva instancia de BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Crea una nueva instancia de UserDetailsService.
     * UserDetailsService es una interfaz que se utiliza para recuperar información del usuario.
     *
     * @return una nueva instancia de UserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailService() {
        return email -> {
            Usuario usuario = userRepository.findByEmail(email);
            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado.");
            }
            return new CustomUserDetails(usuario);
        };
    }
}
