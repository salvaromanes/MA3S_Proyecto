package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.DepositadaEn;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
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
}
