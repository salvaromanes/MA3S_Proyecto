package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

public interface GestionConcesionAutorizacion {
    public void autorizarLectura(PAutorizada persona, Empresa empresa) throws PersonaNoExisteException, EmpresaNoExistenteException;
    public void autorizarOperacion(PAutorizada persona, Empresa empresa) throws EmpresaNoExistenteException, PersonaNoExisteException;
}
