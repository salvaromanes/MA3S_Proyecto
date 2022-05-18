package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.AccesoException;

public interface GestionDepositadaEn {

    void crearDeposito(Pooled pooled, Segregada segregada,double saldo);

}
