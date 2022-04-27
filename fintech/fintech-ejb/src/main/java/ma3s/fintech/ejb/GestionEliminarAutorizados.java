package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;

public interface GestionEliminarAutorizados {
    void darBaja(String usuario, Long idPA, Long idEmpresa) throws PersonaNoExisteException, NoEsPAutorizadaException;


}
