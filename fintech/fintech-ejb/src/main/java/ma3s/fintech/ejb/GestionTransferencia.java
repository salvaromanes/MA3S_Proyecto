package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.Transaccion;
import ma3s.fintech.ejb.excepciones.*;

import java.util.Date;
import java.util.List;

public interface GestionTransferencia {

    public void transferenciaCliente(Transaccion transaccion, Long id) throws PersonaNoExisteException, CampoVacioException,
            ErrorOrigenTransaccionException, SaldoNoSuficiente;

    public void transferenciaAutorizado(Long id, Long idEmpresa, Transaccion transaccion) throws PersonaNoExisteException, ErrorOrigenTransaccionException,
            CampoVacioException, SaldoNoSuficiente, EmpresaNoExistenteException;

    public List<Transaccion> verTransferencias(String iban) throws CuentaNoExistenteException;

    public List<Transaccion> verTransferencias(Segregada segregada);
    public List<Transaccion> verTransferencias2(Pooled pooled);

    public void crearTransaccion(Transaccion t) throws CampoVacioException;

    }
