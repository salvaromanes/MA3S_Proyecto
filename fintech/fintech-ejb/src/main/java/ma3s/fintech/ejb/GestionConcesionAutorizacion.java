package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

public interface GestionConcesionAutorizacion {
    public void autorizarLectura(PAutorizada persona, Empresa empresa) throws UsuarioNoEncontradoException;
    public void autorizarOperacion(Long idPersona, Empresa empresa) throws UsuarioNoEncontradoException;
}
