package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaNoExistenteException;
import ma3s.fintech.excepciones.CuentaNoVacia;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

public interface GestionCierreCuenta {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void cerrarCuenta(String iban) throws CuentaNoExistenteException, CuentaNoVacia;
}
