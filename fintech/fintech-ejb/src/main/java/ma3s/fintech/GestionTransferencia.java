package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.ErrorOrigenTransaccionException;
import ma3s.fintech.excepciones.PersonaNoExisteException;

public interface GestionTransferencia {

    public void transferenciaClienteAutorizado(Long id, String IBAN_origen, String IBAN_destino, double cantidad) throws PersonaNoExisteException, CuentaExistenteException,
            ErrorOrigenTransaccionException;

}
