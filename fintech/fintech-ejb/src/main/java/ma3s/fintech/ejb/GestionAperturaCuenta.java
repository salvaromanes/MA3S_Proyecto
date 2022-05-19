package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.CuentaExistenteException;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

public interface GestionAperturaCuenta {
    //public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void abrirCuentaPooled(String iban, String swift, String usuario, String divisa) throws DivisaExistenteException, CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void abrirCuentaSegregate(String iban, String swift, String usuario) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException;

    public Pooled obtenerDatosCuentaPooled(String IBAN);
    public Segregada obtenerDatosCuentaSegregada(String IBAN);
}
