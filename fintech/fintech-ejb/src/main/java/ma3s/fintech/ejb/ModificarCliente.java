package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.excepciones.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class ModificarCliente implements GestionModificarCliente{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    //Modificacion Empresa
    @Override
    public void modDireccionEmpresa(Long id, String direccion) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(direccion == null)
            throw new CampoVacioException();

        empresa.setDireccion(direccion);
        em.merge(empresa);
    }


    @Override
    public void modCiudadEmpresa(Long id, String ciudad) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(ciudad == null)
            throw new CampoVacioException();

        empresa.setCiudad(ciudad);
        em.merge(empresa);
    }


    @Override
    public void modCodigoPostalEmpresa(Long id, String codigoPostal) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(codigoPostal == null)
            throw new CampoVacioException();

        empresa.setCodigopostal(codigoPostal);
        em.merge(empresa);
    }


    @Override
    public void modPaisEmpresa(Long id, String pais) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(pais == null)
            throw new CampoVacioException();

        empresa.setPais(pais);
        em.merge(empresa);
    }


    @Override
    public void modRazonSocialEmpresa(Long id, String razon_social) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(razon_social == null)
            throw new CampoVacioException();

        empresa.setRazonSocial(razon_social);
        em.merge(empresa);
    }


    //Modificaciones Individual
    @Override
    public void modDireccionIndividual(Long id, String direccion) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(direccion == null)
            throw new CampoVacioException();

        individual.setDireccion(direccion);

        em.merge(individual);
    }


    @Override
    public void modCiudadIndividual(Long id, String ciudad) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(ciudad == null)
            throw new CampoVacioException();

        individual.setCiudad(ciudad);

        em.merge(individual);
    }


    @Override
    public void modCodigoPostalIndividual(Long id, String codigoPostal) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(codigoPostal == null)
            throw new CampoVacioException();

        individual.setCodigopostal(codigoPostal);

        em.merge(individual);
    }


    @Override
    public void modPaisIndividual(Long id, String pais) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(pais == null)
            throw new CampoVacioException();

        individual.setPais(pais);

        em.merge(individual);
    }


    @Override
    public void modNombreIndividual(Long id, String nombre) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(nombre == null)
            throw new CampoVacioException();

        individual.setNombre(nombre);

        em.merge(individual);
    }


    @Override
    public void modApellidoIndividual(Long id, String apellido) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(apellido == null)
            throw new CampoVacioException();

        individual.setApellido(apellido);

        em.merge(individual);
    }


    @Override
    public void modFechaNacimientoIndividual(Long id, Date fecha_nacimiento) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(fecha_nacimiento == null)
            throw new CampoVacioException();

        individual.setFechaNacimiento(fecha_nacimiento);

        em.merge(individual);
    }
}
