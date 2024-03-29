package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Date;

@Stateless
public class ModificarCliente implements GestionModificarCliente{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    //Modificacion Empresa
    @Override
    public void modDireccionEmpresa(Long id, String direccion) throws CampoVacioException, EmpresaNoExistenteException {
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null){
            throw new CampoVacioException();
        }
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(nombre == null) {
            throw new CampoVacioException();
        }

        individual.setNombre(nombre);

        em.merge(individual);
    }


    @Override
    public void modApellidoIndividual(Long id, String apellido) throws CampoVacioException, IndividualNoExistenteException {
        if(id == null)
            throw new CampoVacioException();
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
        if(id == null)
            throw new CampoVacioException();
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }

        individual.setFechaNacimiento(fecha_nacimiento);

        em.merge(individual);
    }


    @Override
    public void modIdentificacionPA(Long id, String identificacion) throws CampoVacioException, PersonaNoExisteException {
        if(id == null)
            throw new CampoVacioException();
        PAutorizada pa = em.find(PAutorizada.class, id);

        if (pa == null){
            throw new PersonaNoExisteException("La persona autorizada con el id " + id + " no existe");
        }
        if(identificacion == null)
            throw new CampoVacioException();

        pa.setIdentificacion(identificacion);

        em.merge(pa);
    }


    @Override
    public void modNombrePA(Long id, String nombre) throws CampoVacioException, PersonaNoExisteException {
        if(id == null)
            throw new CampoVacioException();
        PAutorizada pa = em.find(PAutorizada.class, id);

        if (pa == null){
            throw new PersonaNoExisteException("La persona autorizada con el id " + id + " no existe");
        }
        if(nombre == null)
            throw new CampoVacioException();

        pa.setNombre(nombre);

        em.merge(pa);
    }


    @Override
    public void modApellidosPA(Long id, String apellidos) throws CampoVacioException, PersonaNoExisteException {
        if(id == null)
            throw new CampoVacioException();
        PAutorizada pa = em.find(PAutorizada.class, id);

        if (pa == null){
            throw new PersonaNoExisteException("La persona autorizada con el id " + id + " no existe");
        }
        if(apellidos == null)
            throw new CampoVacioException();

        pa.setApellidos(apellidos);

        em.merge(pa);
    }


    @Override
    public void modDireccionPA(Long id, String direccion) throws CampoVacioException, PersonaNoExisteException {
        if(id == null)
            throw new CampoVacioException();
        PAutorizada pa = em.find(PAutorizada.class, id);

        if (pa == null){
            throw new PersonaNoExisteException("La persona autorizada con el id " + id + " no existe");
        }
        if(direccion == null)
            throw new CampoVacioException();

        pa.setDireccion(direccion);

        em.merge(pa);
    }



}