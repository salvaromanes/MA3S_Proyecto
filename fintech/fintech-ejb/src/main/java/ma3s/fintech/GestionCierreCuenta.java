package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaNoExistenteException;
import ma3s.fintech.excepciones.CuentaNoVacia;

public interface GestionCierreCuenta {
    public void cerrarCuenta(String iban) throws CuentaNoExistenteException, CuentaNoVacia;
}
