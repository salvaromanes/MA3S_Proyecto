package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.ErrorOrigenTransaccionException;

public interface GestionTransferencia {

    public void transferenciaClienteAutorizado(Long id, String IBAN, double cantidad) throws CuentaExistenteException,
            ErrorOrigenTransaccionException;

}
