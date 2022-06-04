package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Stateless
public class GetClientes implements GestionGetClientes{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public List<Cliente> getClientes(){
        Query query = em.createQuery("select c from Cliente c");
        List<Cliente> listaClientes = query.getResultList();
        return listaClientes;
    }

    @Override
    public Cliente devolverCliente(String identificacion) throws ClienteNoExisteException {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
        List<Cliente> list = query.getResultList();

        int i=0;
        boolean encontrado = false;
        while(i<list.size() && !encontrado){
            if(list.get(i).getIdentificacion().equals(identificacion)){
                encontrado = true;
            }else{
                i++;
            }
        }

        Cliente cliente = null;
        if(encontrado){
            cliente = list.get(i);
        }

        if(cliente == null){
            throw new ClienteNoExisteException("Modificar Cliente: el cliente no existe");
        }

        return cliente;
    }

    @Override
    public Individual devolverIndividual(Long id) throws PersonaNoExisteException {
        Individual individual = em.find(Individual.class, id);

        if(individual == null){
            throw new PersonaNoExisteException("Modificar Cliente: el individual no existe");
        }

        return individual;
    }

    @Override
    public Empresa devolverEmpresa(Long id) throws EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null){
            throw new EmpresaNoExistenteException("Modificar Cliente: el individual no existe");
        }

        return empresa;
    }

    @Override
    public Individual devolverIndividual(String identificacion) throws PersonaNoExisteException {
        Query query = em.createQuery("select i from Individual i");
        List<Individual> lista = query.getResultList();

        for(Individual ind : lista){
            // Seleccionamos solo las autorizaciones que pertenezcan a esta empresa
            if(ind.getIdentificacion().equals(identificacion)){
                return ind;
            }
        }

        return null;
    }

    @Override
    public Empresa devolverEmpresa(String identificacion) throws EmpresaNoExistenteException {
        Query query = em.createQuery("select e from Empresa e");
        List<Empresa> lista = query.getResultList();

        for(Empresa emp : lista){
            // Seleccionamos solo las autorizaciones que pertenezcan a esta empresa
            if(emp.getIdentificacion().equals(identificacion)){
                return emp;
            }
        }

        return null;
    }

    @Override
    public List<Empresa> getEmpresas(){
        Query query = em.createQuery("select e from Empresa e");
        List<Empresa> lista = query.getResultList();
        return lista;
    }

    @Override
    public List<Individual> getIndividuales(){
        Query query = em.createQuery("select i from Individual i");
        List<Individual> lista = query.getResultList();
        return lista;
    }

    @Override
    public PAutorizada getPAutorizada(String identificacion) throws EmpresaNoExistenteException {
        Query query = em.createQuery("select p from PAutorizada p");
        List<PAutorizada> lista = query.getResultList();

        for(PAutorizada pa : lista){
            // Seleccionamos solo las autorizaciones que pertenezcan a esta empresa
            if(pa.getIdentificacion().equals(identificacion)){
                return pa;
            }
        }

        return null;
    }

    @Override
    public List<PAutorizada> getPAutorizadas(){
        Query query = em.createQuery("select p from PAutorizada p");
        List<PAutorizada> lista = query.getResultList();
        return lista;
    }

    @Override
    public String getNombre(Long id) throws DatosIncorrectosException {
        String nombre;
        Individual individual = em.find(Individual.class, id);
        if(individual == null){
            Empresa empresa = em.find(Empresa.class, id);
            if(empresa == null){
                throw new DatosIncorrectosException("No hay cliente asociado a ese id");
            }else{
                nombre = empresa.getRazonSocial();
            }
        }else{
            nombre = individual.getNombre();
        }
        return nombre;
    }

    @Override
    public String getApellidos(Long id){
        String apellidos;
        Individual individual = em.find(Individual.class, id);
        if(individual == null){
            apellidos = null;
        }else{
            apellidos = individual.getApellido();
        }
        return apellidos;
    }

    @Override
    public String getFechNac(Long id){
        String fecha;
        Individual individual = em.find(Individual.class, id);
        if(individual == null){
            fecha = "noexistente";
        }else{
            if(individual.getFechaNacimiento() == null){
                fecha = "noexistente";
            }else{
                fecha = individual.getFechaNacimiento().toString();
            }
        }
        return fecha;
    }

    @Override
    public String getFechNacPA(Long id){
        String fecha;
        PAutorizada paut = em.find(PAutorizada.class, id);
        if(paut == null){
            fecha = "noexistente";
        }else{
            if(paut.getFechaNacimiento() == null){
                fecha = "noexistente";
            }else{
                fecha = paut.getFechaNacimiento().toString();
            }
        }
        return fecha;
    }
}