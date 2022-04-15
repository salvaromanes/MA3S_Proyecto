package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;

public interface GestionEliminarAutorizados {
    void darBaja(String usuario, Long idPA) throws PersonaNoExisteException;
    boolean isAdministrativo(String usuario) throws PersonaNoExisteException;
    boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException;
    boolean isPersonaAutorizada(Long idPA, Long idCliente) throws PersonaNoExisteException;

}
