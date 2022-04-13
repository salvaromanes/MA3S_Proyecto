package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;

public interface GestionAperturaCuenta {
    public void abrirCuentaPooled(String iban, String swift) throws CuentaExistenteException;
    public void abrirCuentaSegregate(String iban, String swift) throws CuentaExistenteException;
}
