package ma3s.fintech;

import ma3s.fintech.excepciones.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ModificarCliente implements GestionModificarCliente{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;


    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException();
        }
    }


    public void modIdenEmpresa(Long id, String iden) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(iden == null)
            throw new CampoVacioException();

        empresa.setIdentificacion(iden);
        em.merge(empresa);
    }


    public void modTipoEmpresa(Long id, String tipo_cliente) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(tipo_cliente == null)
            throw new CampoVacioException();

        empresa.setTipoCliente(tipo_cliente);
        em.merge(empresa);
    }


    public void modDireccionEmpresa(Long id, String direccion) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(direccion == null)
            throw new CampoVacioException();

        empresa.setDireccion(direccion);
        em.merge(empresa);
    }


    public void modCodigoPostalEmpresa(Long id, String codigoPostal) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(codigoPostal == null)
            throw new CampoVacioException();

        empresa.setCodigopostal(codigoPostal);
        em.merge(empresa);
    }


    public void modPaisEmpresa(Long id, String pais) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(pais == null)
            throw new CampoVacioException();

        empresa.setPais(pais);
        em.merge(empresa);
    }


    public void modRazonSocialEmpresa(Long id, String razon_social) throws CampoVacioException, EmpresaNoExistenteException {
        Empresa empresa = em.find(Empresa.class, id);

        if(empresa == null)
            throw new EmpresaNoExistenteException("La empresa con el id solicitado "+ id + " no existe");
        if(razon_social == null)
            throw new CampoVacioException();

        empresa.setRazonSocial(razon_social);
        em.merge(empresa);
    }


    public void modIdenIndividual(Long id, String iden) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(iden == null)
            throw new CampoVacioException();

        individual.setIdentificacion(iden);

        em.merge(individual);
    }


    public void modTipoClienteIndividual(Long id, String tipo_cliente) throws CampoVacioException, IndividualNoExistenteException {
        Individual individual = em.find(Individual.class, id);

        if (individual == null){
            throw new IndividualNoExistenteException("El individual con el id " + id + " no existe");
        }
        if(tipo_cliente == null)
            throw new CampoVacioException();

        individual.setTipoCliente(tipo_cliente);

        em.merge(individual);
    }


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


}
