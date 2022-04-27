package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.*;

public interface GestionBajaCliente {
    public void darBajaCliente(Long id) throws CampoVacioException, CuentaAbiertaException, ClienteNoExisteException;
}
