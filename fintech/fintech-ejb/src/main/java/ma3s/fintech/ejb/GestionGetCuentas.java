package ma3s.fintech.ejb;

import ma3s.fintech.*;

import javax.persistence.Query;
import java.util.List;

public interface GestionGetCuentas {
    public List<Cuenta> getCuentas();

    public List<Segregada> getSegregada(String iban);

    public List<Segregada> getSegregadas(Usuario usuario);

    public List<Segregada> getSegregadas();

    public List<Pooled> getPooled (String iban);

    public List<Pooled> getPooleds(Usuario usuario);

    public List<Pooled> getPooleds();

    public Referencia getReferencia(String iban);

    public List<Referencia> getReferencias();

    public List<Autorizacion> getAutorizaciones (String iban, String ident);

    public List<Referencia> getReferenciaSegregada(String iban);

    public List<DepositadaEn> getReferenciaPooled(String iban);

    public List<Segregada> getSegregadasAuto(Usuario usuario);


    }


