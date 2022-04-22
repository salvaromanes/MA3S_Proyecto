package ma3s.fintech;

import ma3s.fintech.excepciones.*;

public interface GestionBajaCliente {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, NoEsAdministrativoException;
    public void darBajaCliente(Long id) throws CampoVacioException, CuentaAbiertaException, ClienteNoExisteException;
}
