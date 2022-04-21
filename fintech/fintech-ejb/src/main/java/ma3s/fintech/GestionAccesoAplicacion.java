package ma3s.fintech;

import ma3s.fintech.excepciones.*;

import java.util.List;

public interface GestionAccesoAplicacion {

    void accederAplicacion(String usuario) throws PersonaNoExisteException, AccesoException, CuentaExistenteException;
    boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException;
    boolean isPersonaAutorizada(Long idPA, Long idCliente) throws PersonaNoExisteException;

}
