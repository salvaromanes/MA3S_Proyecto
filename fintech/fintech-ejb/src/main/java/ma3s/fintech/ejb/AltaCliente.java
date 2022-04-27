package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.NoEsAdministrativoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AltaCliente implements GestionAltaCliente{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, NoEsAdministrativoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if (!user.getEsAdmin()){
            throw new NoEsAdministrativoException();
        }
    }


    public void darAltaEmpresa(Empresa empresa) throws CampoVacioException {
        Long id = empresa.getId();
        String identificacion = empresa.getIdentificacion();
        String tipo_cliente = empresa.getTipoCliente();
        String direccion = empresa.getDireccion();
        String ciudad = empresa.getCiudad();
        String codigoPostal = empresa.getCodigopostal();
        String pais = empresa.getPais();
        String razon_social = empresa.getRazonSocial();
        if(id == null || identificacion == null || tipo_cliente == null || direccion == null || ciudad == null || codigoPostal == null || pais == null || razon_social == null)
            throw new CampoVacioException();

        em.persist(empresa);
    }


    public void darAltaIndividual(Individual individual) throws CampoVacioException {
        Long id = individual.getId();
        String identificacion = individual.getIdentificacion();
        String tipo_cliente = individual.getTipoCliente();
        String direccion = individual.getDireccion();
        String ciudad = individual.getCiudad();
        String codigoPostal = individual.getCodigopostal();
        String pais = individual.getPais();
        String nombre = individual.getNombre();
        String apellido = individual.getApellido();
        if(id == null || identificacion == null || tipo_cliente == null || direccion == null || ciudad == null || codigoPostal == null || pais == null || nombre == null || apellido == null)
            throw new CampoVacioException();

        em.persist(individual);
    }
}
