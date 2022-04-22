package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.ErrorOrigenTransaccionException;
import ma3s.fintech.excepciones.PersonaNoExisteException;
import ma3s.fintech.excepciones.TransaccionYaExistente;

public interface GestionTransferencia {

    public void transferenciaClienteAutorizado(Long id, String idTransaccion, String IBAN_origen, String IBAN_destino, double cantidad) throws PersonaNoExisteException, CuentaExistenteException,
            ErrorOrigenTransaccionException, TransaccionYaExistente;

}
