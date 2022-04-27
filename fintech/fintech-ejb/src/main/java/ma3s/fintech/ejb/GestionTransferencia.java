package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.*;

import java.util.Date;

public interface GestionTransferencia {

    public void transferenciaCliente(Long id, String idTransaccion, String IBAN_origen, String IBAN_destino, double cantidad, String divisaOrigen, Date fechaInstruccion)
    throws PersonaNoExisteException, CuentaExistenteException, ErrorOrigenTransaccionException, TransaccionYaExistente, SaldoNoSuficiente;

    public void transferenciaAutorizado(Long id, String idTransaccion, String IBAN_origen, String IBAN_destino, double cantidad, String divisaOrigen, Long idEmpresa, Date fechaInstruccion)
    throws PersonaNoExisteException, CuentaExistenteException, ErrorOrigenTransaccionException, TransaccionYaExistente, SaldoNoSuficiente;

}
