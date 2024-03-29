package edu.unc.auth_eventos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Gestiona las excepciones globales, permite controlar y personalizar las respuestas de errores en la aplicación.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Maneja la excepción EntityNotFoundException.
     *
     * @param ex      la excepción lanzada.
     * @param request la solicitud web que resultó en una excepción.
     * @return una respuesta HTTP personalizada con detalles de error.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja la excepción IllegalOperationException.
     *
     * @param ex      la excepción lanzada.
     * @param request la solicitud web que resultó en una excepción.
     * @return una respuesta HTTP personalizada.
     */
    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<?> handleIllegalOperationException(IllegalOperationException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja la excepción generales.
     *
     * @param ex      la excepción general.
     * @param request la solicitud web asociada.
     * @return una respuesta HTTP personalizada con detalles de error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Maneja la excepción BadCredentialsException.
     *
     * @param ex      la excepción lanzada.
     * @param request la solicitud web que resultó en una excepción.
     * @return una respuesta HTTP personalizada.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}

