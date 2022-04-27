package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.*;

public interface GestionBajaCliente {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, NoEsAdministrativoException;
    public void darBajaCliente(Long id) throws CampoVacioException, CuentaAbiertaException, ClienteNoExisteException;
}
