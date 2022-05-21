package ma3s.fintech.ejb;

import ma3s.fintech.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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
    public List<Segregada> getSegregadas(Usuario usuario){
        List<Segregada> aux = new ArrayList<>();
        Query query = em.createQuery("select c from Segregada c", Segregada.class);
        List<Segregada> listaClientes = query.getResultList();
        for(Segregada cuenta: listaClientes){
            if(cuenta.getCliente().getUser().getUser().equals(usuario.getUser())){
                aux.add(cuenta);
            }
        }
        return aux;
    }

    @Override
    public Pooled getPooled (String iban) {
        return em.find(Pooled.class,iban);
    }

    @Override
    public List<Pooled> getPooleds(Usuario usuario){
        List<Pooled> aux = new ArrayList<>();
        Query query = em.createQuery("select c from Pooled c");
        List<Pooled> listaClientes = query.getResultList();
        for(Pooled cuenta2 : listaClientes){
            if(cuenta2.getCliente().getUser().getUser().equals(usuario.getUser())){
                aux.add(cuenta2);
            }
        }
        return aux;
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

