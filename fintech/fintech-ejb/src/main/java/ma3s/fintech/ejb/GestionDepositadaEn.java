package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.*;

public interface GestionDepositadaEn {

    void crearDeposito(Pooled pooled, Segregada segregada, double saldo, Referencia referencia) throws PooledException, SegregadaException, SaldoNoSuficiente;

}
