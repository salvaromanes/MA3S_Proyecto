package ma3s.fintech.ejb;

import ma3s.fintech.Transaccion;
import ma3s.fintech.ejb.excepciones.*;

import java.util.Date;

public interface GestionTransferencia {

    public void transferenciaCliente(Transaccion transaccion, Long id) throws PersonaNoExisteException, CampoVacioException, ErrorOrigenTransaccionException, SaldoNoSuficiente;

    public void transferenciaAutorizado(Long id, Long idEmpresa, Transaccion transaccion) throws PersonaNoExisteException, ErrorOrigenTransaccionException, CampoVacioException, SaldoNoSuficiente;

}
