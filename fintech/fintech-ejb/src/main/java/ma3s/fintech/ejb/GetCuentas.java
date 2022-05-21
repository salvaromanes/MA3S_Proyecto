package ma3s.fintech.ejb;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Pooled;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class GetCuentas implements GestionGetCuentas{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public List<Cuenta> getCuentas(){
        Query query = em.createQuery("select c from Cliente c");
        List<Cuenta> listaClientes = query.getResultList();
        return listaClientes;
    }

    @Override
    public Segregada getSegregada(String iban) {
        return em.find(Segregada.class,iban);
    }

    @Override
    public List<Segregada> getSegregadas(){
        Query query = em.createQuery("select c from Segregada c");
        List<Segregada> listaClientes = query.getResultList();
        return listaClientes;
    }

    @Override
    public Pooled getPooled (String iban) {
        return em.find(Pooled.class,iban);
    }

    @Override
    public List<Pooled> getPooleds(){
        Query query = em.createQuery("select c from Pooled c");
        List<Pooled> listaClientes = query.getResultList();
        return listaClientes;
    }

    @Override
    public Referencia getReferencia(String iban) {
        return em.find(Referencia.class,iban);
    }

    @Override
    public List<Referencia> getReferencias(){
        Query query = em.createQuery("select c from Referencia c");
        List<Referencia> listaClientes = query.getResultList();
        return listaClientes;
    }

}

