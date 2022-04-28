package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;

public interface GestionEliminarAutorizados {
    void darBaja(String usuario, PAutorizada pa, Empresa empresa) throws PersonaNoExisteException, NoEsPAutorizadaException, EmpresaNoExistenteException;


}
