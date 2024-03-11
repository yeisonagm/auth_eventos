/**
 * @file: Usuario.java
 * @author: (c)2024 Yeison García
 * @created: Mar 10, 2024 04:03:47 PM
 */
package edu.unc.auth_eventos.service;

import edu.unc.auth_eventos.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Clase que representa el servicio de token de autenticación.
 * <p>
 * Esta clase es un servicio que se utiliza para definir los métodos de token de autenticación.
 */
@Service
public class JwtServiceImp implements JwtService {
    /**
     * Método para generar un token de autenticación.
     *
     * @param user con la información del usuario.
     * @return El token de autenticación.
     */
    @Override
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * Método para generar un token de autenticación.
     *
     * @param extraClaims Los claims adicionales.
     * @param user        con la información del usuario.
     * @return El token de autenticación.
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_ACCESS_TOKEN))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Método para obtener el nombre de usuario a partir de un token de autenticación.
     *
     * @param token El token de autenticación.
     * @return El nombre de usuario.
     */
    @Override
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Método para obtener el claim de un token de autenticación.
     *
     * @param token          El token de autenticación.
     * @param claimsResolver El resolver de claims.
     * @return El claim del token.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Método para validar un token de autenticación.
     *
     * @param token       El token de autenticación.
     * @param userDetails Los detalles del usuario.
     * @return true si el token es válido, false de lo contrario.
     */
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Método para obtener la clave secreta.
     *
     * @return La clave secreta.
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtUtil.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Método para obtener todos los claims de un token de autenticación.
     *
     * @param token El token de autenticación.
     * @return Todos los claims.
     */
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Método para obtener la fecha de expiración de un token de autenticación.
     *
     * @param token El token de autenticación.
     * @return La fecha de expiración.
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Método para verificar si un token de autenticación está expirado.
     *
     * @param token El token de autenticación.
     * @return true si el token está expirado, false de lo contrario.
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
