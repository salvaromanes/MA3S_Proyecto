package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.*;

public interface GestionAccesoAplicacion {

    void accederAplicacion(String usuario) throws PersonaNoExisteException, AccesoException, CuentaExistenteException;
    boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException;
    boolean isPersonaAutorizada(Long idPA, Long idCliente) throws PersonaNoExisteException;

}
