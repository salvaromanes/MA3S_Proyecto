package ma3s.fintech.ejb;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Pooled;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;

import javax.persistence.Query;
import java.util.List;

public interface GestionGetCuentas {
    public List<Cuenta> getCuentas();

    public Segregada getSegregada(String iban);

    public List<Segregada> getSegregadas();

    public Pooled getPooled (String iban);

    public List<Pooled> getPooleds();

    public Referencia getReferencia(String iban);

    public List<Referencia> getReferencias();
}


