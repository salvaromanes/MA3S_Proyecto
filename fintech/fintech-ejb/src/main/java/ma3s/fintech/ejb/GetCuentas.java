package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteNoExisteException;
import ma3s.fintech.ejb.excepciones.DatosIncorrectosException;

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
    public List<Fintech> getFintech(){
        Query query = em.createQuery("select c from Fintech c");
        List<Fintech> listaCuentasF = query.getResultList();
        return listaCuentasF;
    }

    @Override
    public Cuenta getCuenta(String iban) throws ClienteNoExisteException {
        Cuenta cuenta = em.find(Cuenta.class, iban);
        if(cuenta == null)
            throw new ClienteNoExisteException();
        return cuenta;
    }

    @Override
    public List<Segregada> getSegregada(String iban) {
        Query query = em.createQuery("select c from Segregada c where c.iban like :idCuenta").setParameter("idCuenta", iban);
        return query.getResultList();
    }

    @Override
    public List<Segregada> getSegregadas(Usuario usuario){
        Usuario user = em.find(Usuario.class, usuario.getUser());
        List<Segregada> listaSeg = new ArrayList<>();
        if(user == null){
            return listaSeg;
        }
        Cliente c = user.getCliente();
        if(c == null)
            return listaSeg;
        List<Fintech> lista = c.getCuentasFintech();
        if(lista ==null)
            return listaSeg;

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
        Usuario user = em.find(Usuario.class, usuario.getUser());
        List<Pooled> listaSeg = new ArrayList<>();
        if(user == null){
            return listaSeg;
        }
        Cliente c = user.getCliente();
        if(c == null)
            return listaSeg;
        List<Fintech> lista = c.getCuentasFintech();
        if(lista ==null)
            return listaSeg;

        for (Fintech f : lista){
            Pooled s = em.find(Pooled.class, f.getIban());
            if(s != null)
                listaSeg.add(s);
        }
        return listaSeg;
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
        List<Autorizacion> result = new ArrayList<>();

        //Query query = em.createQuery("Select c from Autorizacion c where c.empresaId.identificacion = :identificacion").setParameter("identificacion", ident);
        Query query = em.createQuery("Select c from Autorizacion c");
        List<Autorizacion> listaAutorizaciones = query.getResultList();

        if(listaAutorizaciones != null){
            for(Autorizacion auto : listaAutorizaciones){
                if(auto.getEmpresaId().getIdentificacion().equalsIgnoreCase(ident)) result.add(auto);
            }
        }


        return result;
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

    @Override
    public List<Segregada> getSegregadasAutoLec(Usuario usuario){
        Usuario user = em.find(Usuario.class, usuario.getUser());
        if(user == null){
            return null;
        }
        List<Fintech> lista = new ArrayList<>();
        List<Empresa> empresaList = new ArrayList<>();
        List<Segregada> listaSeg = new ArrayList<>();
        if(user.getAutorizada() == null)
            return listaSeg;
        List<Autorizacion> autorizacionLista = user.getAutorizada().getAutorizaciones();

        if(autorizacionLista == null)
            return listaSeg;

        for (Autorizacion f : autorizacionLista){
            if(f.getTipo().equalsIgnoreCase("LECTURA")){
                empresaList.add(f.getEmpresaId());
            }
        }

        for(Empresa e : empresaList){
            lista.addAll(e.getCuentasFintech());
        }

        for(Fintech g: lista){
            Segregada s = em.find(Segregada.class, g.getIban());
            if(s != null)
                listaSeg.add(s);
        }
        return listaSeg;
    }



    @Override
    public List<Segregada> getSegregadasAutoEsc(Usuario usuario){
        Usuario user = em.find(Usuario.class, usuario.getUser());
        if(user == null){
            return null;
        }
        List<Fintech> lista = new ArrayList<>();
        List<Empresa> empresaList = new ArrayList<>();
        List<Segregada> listaSeg = new ArrayList<>();
        if(user.getAutorizada() == null)
            return listaSeg;
        List<Autorizacion> autorizacionLista = user.getAutorizada().getAutorizaciones();

        if(autorizacionLista == null)
            return listaSeg;

        for (Autorizacion f : autorizacionLista){
            if(f.getTipo().equalsIgnoreCase("ESCRITURA")){
                empresaList.add(f.getEmpresaId());
            }
        }

        for(Empresa e : empresaList){
            lista.addAll(e.getCuentasFintech());
        }

        for(Fintech g: lista){
            Segregada s = em.find(Segregada.class, g.getIban());
            if(s != null)
                listaSeg.add(s);
        }
        return listaSeg;
    }



    @Override
    public List<Pooled> getPooledAutLec(Usuario usuario){
        Usuario user = em.find(Usuario.class, usuario.getUser());
        if(user == null){
            return null;
        }
        List<Fintech> lista = new ArrayList<>();
        List<Empresa> empresaList = new ArrayList<>();
        List<Pooled> listaPooled = new ArrayList<>();
        if(user.getAutorizada() == null)
            return listaPooled;

        List<Autorizacion> autorizacionLista = user.getAutorizada().getAutorizaciones();

        if (autorizacionLista == null)
            return listaPooled;
        for (Autorizacion f : autorizacionLista){
            if(f.getTipo().equalsIgnoreCase("LECTURA")){
                empresaList.add(f.getEmpresaId());
            }
        }

        for(Empresa e : empresaList){
            lista.addAll(e.getCuentasFintech());
        }

        for(Fintech g: lista){
            Pooled s = em.find(Pooled.class, g.getIban());
            if(s != null)
                listaPooled.add(s);
        }
        return listaPooled;
    }


    @Override
    public List<Pooled> getPooledAutEsc(Usuario usuario){
        Usuario user = em.find(Usuario.class, usuario.getUser());
        if(user == null){
            return null;
        }
        List<Fintech> lista = new ArrayList<>();
        List<Empresa> empresaList = new ArrayList<>();
        List<Pooled> listaPooled = new ArrayList<>();
        if(user.getAutorizada() == null)
            return listaPooled;

        List<Autorizacion> autorizacionLista = user.getAutorizada().getAutorizaciones();

        if (autorizacionLista == null)
            return listaPooled;
        for (Autorizacion f : autorizacionLista){
            if(f.getTipo().equalsIgnoreCase("ESCRITURA")){
                empresaList.add(f.getEmpresaId());
            }
        }

        for(Empresa e : empresaList){
            lista.addAll(e.getCuentasFintech());
        }

        for(Fintech g: lista){
            Pooled s = em.find(Pooled.class, g.getIban());
            if(s != null)
                listaPooled.add(s);
        }
        return listaPooled;
    }

    @Override
    public Divisa getDivisa(String di) throws DatosIncorrectosException{
        Divisa divisa = em.find(Divisa.class, di);
        if(divisa == null)
            throw new DatosIncorrectosException();
        return divisa;
    }

    @Override
    public Pooled getUPooled(String iban){
        Pooled pooled = em.find(Pooled.class, iban);
        if(pooled == null)
            return null;
        return pooled;
    }


    @Override
    public Segregada getUSegregada(String iban){
        Segregada segregada = em.find(Segregada.class, iban);
        if(segregada == null)
            return null;
        return segregada;
    }
}

