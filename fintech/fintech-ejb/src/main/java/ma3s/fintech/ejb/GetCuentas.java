package ma3s.fintech.ejb;

import ma3s.fintech.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class GetCuentas implements GestionGetCuentas{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public List<Cuenta> getCuentas(){
        Query query = em.createQuery("select c from Cuenta c");
        List<Cuenta> listaClientes = query.getResultList();
        return listaClientes;
    }

    @Override
    public List<Segregada> getSegregada(String iban) {
        Query query = em.createQuery("select c from Segregada c where c.iban like :idCuenta").setParameter("idCuenta", iban);
        return query.getResultList();
    }

    @Override
    public List<Segregada> getSegregadas(Usuario usuario){
       Usuario user = em.find(Usuario.class, usuario.getUser());
       if(user == null){
           return null;
       }
       List<Fintech> lista = user.getCliente().getCuentasFintech();
       List<Segregada> listaSeg = new ArrayList<>();
       for (Fintech f : lista){
           Segregada s = em.find(Segregada.class, f.getIban());
           if(s != null)
               listaSeg.add(s);
       }
       return listaSeg;
    }

    @Override
    public List<Segregada> getSegregadas(){
        Query query = em.createQuery("select s from Segregada s");
        List<Segregada> lista = query.getResultList();

        return lista;
    }

    @Override
    public List<Pooled> getPooled (String iban) {
        //List<Pooled> aux = new ArrayList<>();
        Query query = em.createQuery("select c from Pooled c where c.iban like :idCuenta").setParameter("idCuenta", iban);
        return query.getResultList();
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
    public List<Pooled> getPooleds(){
        Query query = em.createQuery("select p from Pooled p");
        List<Pooled> lista = query.getResultList();

        return lista;
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

    @Override
    public List<Autorizacion> getAutorizaciones (String iban, String ident){
        List<Autorizacion> listaAutorizaciones = new ArrayList<>();

        // Creamos la consulta para obtener todas las autorizaciones
        Query query = em.createQuery("select c from Autorizacion c");

        for(Autorizacion auto : (List<Autorizacion>) query.getResultList()){
            // Seleccionamos solo las autorizaciones que pertenezcan a esta empresa
            if(auto.getEmpresaId().getIdentificacion().equals(ident)){
                listaAutorizaciones.add(auto);
            }
        }

        return listaAutorizaciones;
    }

    @Override
    public List<Referencia> getReferenciaSegregada(String iban){
        //Query query = em.createQuery("select c from Referencia c where c.segregada.iban like :idCuenta").setParameter("idCuenta", iban);
        //return query.getResultList();

        List<Referencia> listaReferencia = new ArrayList<>();

        // Creamos la consulta para obtener todas las autorizaciones
        Query query = em.createQuery("select c from Referencia c");

        for(Referencia ref : (List<Referencia>) query.getResultList()){
            // Seleccionamos solo las autorizaciones que pertenezcan a esta empresa
            if(ref.getSegregada().getIban().equals(iban)){
                listaReferencia.add(ref);
            }
        }

        return listaReferencia;
    }

    @Override
    public List<DepositadaEn> getReferenciaPooled(String iban){
        //Query query = em.createQuery("select c from Referencia c where c.segregada.iban like :idCuenta").setParameter("idCuenta", iban);
        //return query.getResultList();

        List<DepositadaEn> listaDepositadas = new ArrayList<>();

        // Creamos la consulta para obtener todas las autorizaciones
        Query query = em.createQuery("select c from DepositadaEn c");

        for(DepositadaEn dep : (List<DepositadaEn>) query.getResultList()){
            // Seleccionamos solo las autorizaciones que pertenezcan a esta empresa
            if(dep.getIbanPooled().getIban().equals(iban)){
                listaDepositadas.add(dep);
            }
        }

        return listaDepositadas;
    }

}

