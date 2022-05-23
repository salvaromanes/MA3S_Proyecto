package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.*;

public interface GestionAperturaCuenta {
    //public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void abrirCuentaPooled(String iban, String swift, String usuario, String divisa, String identificacion) throws DivisaExistenteException, CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException, ClienteNoExisteException;
    public void abrirCuentaSegregate(String iban, String swift, String usuario, String identificacion) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException, ClienteNoExisteException;

    public Pooled obtenerDatosCuentaPooled(String IBAN);
    public Segregada obtenerDatosCuentaSegregada(String IBAN);
}
