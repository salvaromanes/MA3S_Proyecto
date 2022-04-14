package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;

public interface GestionAccesoAplicacion {
    void accederAplicacion(Long idCliente) throws PersonaNoExisteException;
    boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException;
    boolean isPersonaAutorizada(Long idPA, Long idCliente) throws PersonaNoExisteException;
}
