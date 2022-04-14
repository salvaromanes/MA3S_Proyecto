package ma3s.fintech;

import ma3s.fintech.excepciones.CampoVacioException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AltaCliente implements GestionAltaCliente{
    @PersistenceContext(name = "fintech")
    private EntityManager em;

    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if (!user.getEsAdmin()){
            throw new UsuarioIncorrectoException();
        }
    }


    public void darAltaEmpresa(Long id, String identificacion, String tipo_cliente, String direccion, String ciudad, String codigoPostal, String pais, String razon_social) throws CampoVacioException {
        if(id == null || identificacion == null || tipo_cliente == null || direccion == null || ciudad == null || codigoPostal == null || pais == null || razon_social == null)
            throw new CampoVacioException();

        Empresa empresa = new Empresa();
        empresa.setId(id);
        empresa.setIdentificacion(identificacion);
        empresa.setTipoCliente(tipo_cliente);
        empresa.setDireccion(direccion);
        empresa.setCiudad(ciudad);
        empresa.setCodigopostal(codigoPostal);
        empresa.setPais(pais);
        empresa.setRazonSocial(razon_social);

        em.persist(empresa);
    }


    public void darAltaIndividual(Long id, String identificacion, String tipo_cliente, String direccion, String ciudad, String codigoPostal, String pais, String nombre, String apellido) throws CampoVacioException {
        if(id == null || identificacion == null || tipo_cliente == null || direccion == null || ciudad == null || codigoPostal == null || pais == null || nombre == null || apellido == null)
            throw new CampoVacioException();
        Individual individual = new Individual();
        individual.setId(id);
        individual.setIdentificacion(identificacion);
        individual.setTipoCliente(tipo_cliente);
        individual.setDireccion(direccion);
        individual.setCiudad(ciudad);
        individual.setCodigopostal(codigoPostal);
        individual.setPais(pais);
        individual.setNombre(nombre);
        individual.setApellido(apellido);

        em.persist(individual);
    }
}
