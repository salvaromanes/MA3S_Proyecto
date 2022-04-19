package ma3s.fintech;

import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

public interface GestionConcesionAutorizacion {
    public void autorizarLectura(String nombre, String apellidos, String identificacion, Long id) throws UsuarioNoEncontradoException;
    public void autorizarOperacion(String nombre, String apellidos, String identificacion, Long id) throws UsuarioNoEncontradoException;
}
