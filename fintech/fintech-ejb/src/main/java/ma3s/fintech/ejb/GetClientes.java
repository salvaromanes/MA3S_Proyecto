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
        return null;
    }

    @Override
    public Individual devolverIndividual(Long id) throws PersonaNoExisteException {
        return null;
    }

    @Override
    public Empresa devolverEmpresa(Long id) throws EmpresaNoExistenteException {
        return null;
    }
}
