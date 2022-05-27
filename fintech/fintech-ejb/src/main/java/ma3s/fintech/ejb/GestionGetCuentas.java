package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.ClienteNoExisteException;
import ma3s.fintech.ejb.excepciones.DatosIncorrectosException;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;

import javax.persistence.Query;
import java.util.List;

public interface GestionGetCuentas {
    public List<Cuenta> getCuentas();

    public Cuenta getCuenta(String iban) throws ClienteNoExisteException;

    public List<Segregada> getSegregada(String iban);

    public List<Segregada> getSegregadas(Usuario usuario);

    public List<Segregada> getSegregadas();

    public List<Pooled> getPooled (String iban);

    public List<Pooled> getPooleds(Usuario usuario);

    public List<Pooled> getPooleds();

    public Referencia getReferencia(String iban);

    public List<Referencia> getReferencias();

    public List<Autorizacion> getAutorizaciones (String iban, String ident);

    public List<Fintech> getAutorizacionesCliente (String ident);

    public List<Referencia> getReferenciaSegregada(String iban);

    public List<DepositadaEn> getReferenciaPooled(String iban);

    public List<Segregada> getSegregadasAutoLec(Usuario usuario);

    public List<Segregada> getSegregadasAutoEsc(Usuario usuario);

    public List<Pooled> getPooledAutLec(Usuario usuario);

    public List<Pooled> getPooledAutEsc(Usuario usuario);

    public Divisa getDivisa(String divisa) throws DatosIncorrectosException;

    public Pooled getUPooled(String iban);

    public Segregada getUSegregada(String iban);

    List<Fintech> getFintech();

    List<Fintech> getFintechSemanal();
    }


