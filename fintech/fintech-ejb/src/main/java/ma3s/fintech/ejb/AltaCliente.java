package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteYaExistenteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class AltaCliente implements GestionAltaCliente{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void darAltaEmpresa(Empresa empresa) throws CampoVacioException, ClienteYaExistenteException {
        if(empresa == null)
            throw new CampoVacioException();
        String identificacion = empresa.getIdentificacion();
        String tipo_cliente = empresa.getTipoCliente();
        String estado = empresa.getEstado();
        Date fecha_alta = empresa.getFechaAlta();
        String direccion = empresa.getDireccion();
        String ciudad = empresa.getCiudad();
        String codigoPostal = empresa.getCodigopostal();
        String pais = empresa.getPais();
        String razon_social = empresa.getRazonSocial();
        if(identificacion == null || tipo_cliente == null
                || estado == null || fecha_alta == null
                || direccion == null || ciudad == null
                || codigoPostal == null || pais == null
                || razon_social == null)
            throw new CampoVacioException();

        Query query = em.createQuery("select c from Cliente c");
        List<Cliente> listaClientes = query.getResultList();

        for(Cliente c : listaClientes){
            if(c.getIdentificacion().equals(identificacion))
                throw new ClienteYaExistenteException();
        }

        em.persist(empresa);
    }


    @Override
    public void darAltaIndividual(Individual individual) throws CampoVacioException, ClienteYaExistenteException {
        if(individual == null)
            throw new CampoVacioException();
        String identificacion = individual.getIdentificacion();
        String tipo_cliente = individual.getTipoCliente();
        String estado = individual.getEstado();
        Date fecha_alta = individual.getFechaAlta();
        String direccion = individual.getDireccion();
        String ciudad = individual.getCiudad();
        String codigoPostal = individual.getCodigopostal();
        String pais = individual.getPais();
        String nombre = individual.getNombre();
        String apellido = individual.getApellido();
        if(identificacion == null || tipo_cliente == null
                || estado == null || fecha_alta == null
                || direccion == null || ciudad == null
                || codigoPostal == null || pais == null
                || nombre == null || apellido == null)
            throw new CampoVacioException();

        Query query = em.createQuery("select c from Cliente c");
        List<Cliente> listaClientes = query.getResultList();

        for(Cliente c : listaClientes){
            if(c.getIdentificacion().equals(identificacion))
                throw new ClienteYaExistenteException();
        }

        em.persist(individual);
    }
}
