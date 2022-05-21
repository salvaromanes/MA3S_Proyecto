package ma3s.fintech.ejb;

import ma3s.fintech.*;

import javax.persistence.Query;
import java.util.List;

public interface GestionGetCuentas {
    public List<Cuenta> getCuentas();

    public Segregada getSegregada(String iban);

    public List<Segregada> getSegregadas(Usuario usuario);

    public Pooled getPooled (String iban);

    public List<Pooled> getPooleds(Usuario usuario);

    public Referencia getReferencia(String iban);

    public List<Referencia> getReferencias();
}


