package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.CuentaNoExistenteException;
import ma3s.fintech.ejb.excepciones.CuentaNoVacia;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

public interface GestionCierreCuenta {
    //public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void cerrarCuenta(String iban, String usuario) throws CuentaNoExistenteException, CuentaNoVacia, UsuarioNoEncontradoException, UsuarioIncorrectoException;
}
