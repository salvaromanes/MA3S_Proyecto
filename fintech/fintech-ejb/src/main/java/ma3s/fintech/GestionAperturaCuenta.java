package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.CuentaNoExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

public interface GestionAperturaCuenta {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void abrirCuentaPooled(String iban, String swift) throws CuentaExistenteException;
    public void abrirCuentaSegregate(String iban, String swift) throws CuentaExistenteException;
}
