package ma3s.fintech;

import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

public interface GestionConcesionAutorizacion {
    public void autorizarLectura(Long idPersona, Empresa empresa) throws UsuarioNoEncontradoException;
    public void autorizarOperacion(Long idPersona, Empresa empresa) throws UsuarioNoEncontradoException;
}
