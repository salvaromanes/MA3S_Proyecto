package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.*;

public interface GestionDepositadaEn {

    void crearDeposito(Pooled pooled, double saldo, Referencia referencia) throws PooledException, SaldoNoSuficiente, ReferenciaException;

    void actualizarDeposito(Pooled pooled, double saldo, Referencia referencia) throws PooledException, SaldoNoSuficiente, ReferenciaException;
}
