package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExiste;

public interface GestionEliminarAutorizados {
    public void darBaja(Long idAdministrativo, Long idPA) throws PersonaNoExiste;
    public boolean isPersonaJuridica(Long id, String tipoCliente) throws PersonaNoExiste;
}
