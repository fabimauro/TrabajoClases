package co.edu.uniquindio.biblioteca.servicio.excepciones;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String mensaje){
        super(mensaje);
    }

}
