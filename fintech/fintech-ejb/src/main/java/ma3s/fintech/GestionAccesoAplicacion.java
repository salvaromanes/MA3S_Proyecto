package ma3s.fintech;

import ma3s.fintech.excepciones.ContraseñaIncorrectaException;
import ma3s.fintech.excepciones.PersonaNoExisteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;

public interface GestionAccesoAplicacion {
    void accederAplicacion(Long idCliente, String usuario, String contrasena) throws PersonaNoExisteException, UsuarioIncorrectoException, ContraseñaIncorrectaException;
    boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException;
    boolean isPersonaAutorizada(Long idPA, Long idCliente) throws PersonaNoExisteException;
}
