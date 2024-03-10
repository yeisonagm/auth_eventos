package edu.unc.auth_eventos.exception;

import java.io.Serial;

/**
 * Excepción que se lanza cuando el proceso de búsqueda no encuentra una entidad,
 */
public class EntityNotFoundException extends RuntimeException {
    /**
     * Serial único de la clase, se utiliza durante la deserialización para verificar la compatibilidad de la clase.
     * Este valor ayuda a garantizar que la clase utilizada para serializar los objetos es compatible con la clase utilizada para deserializarlos.
     * Si se realiza algún cambio en la clase (como agregar métodos o propiedades), este valor debe generarse nuevamente.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de la clase EntityNotFoundException.
     *
     * @param message Mensaje detallado del error.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
