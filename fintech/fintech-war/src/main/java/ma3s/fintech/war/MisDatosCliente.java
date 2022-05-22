package ma3s.fintech.war;

import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Named(value = "cliente")
@RequestScoped
public class MisDatosCliente {

    private static final Logger LOGGER = Logger.getLogger(MisDatosCliente.class.getCanonicalName());



    private Long id = 1L;
    private String identificacion = "P3310693A";
    private String tipoCliente = "Jurídico";
    private String estado = "Activo";
    private Date fechaAlta = new Date();
    private Date fechaBaja = null;
    private String direccion = "Buleavaur";
    private String ciudad = "Málaga";
    private String codigopostal = "29004";
    private String pais = "Espana";

    @Inject
    private GestionModificarCliente gestionModificarCliente;
    @Inject
    private GestionGetClientes gestionGetClientes;

    private Cliente cliente;

    private Individual individual;

    private Empresa empresa;

    public MisDatosCliente(){ }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


    public String pasarCliente(String identificacion) {
        try {
            cliente = gestionGetClientes.devolverCliente(identificacion);

            modificar(cliente);

            return "MisDatosClientes.xhtml";
        } catch (ClienteNoExisteException e) {
            LOGGER.info("ClienteNoExisteException " + e.getMessage());
        }
        return "index.xhtml";
    }


    public String modificar(Cliente c) {
        try {
            individual = gestionGetClientes.devolverIndividual(c.getId());
            empresa = gestionGetClientes.devolverEmpresa(c.getId());

            if(c.getTipoCliente().equals("Individual")){
                gestionModificarCliente.modNombreIndividual(individual.getId(), individual.getNombre());
                gestionModificarCliente.modApellidoIndividual(individual.getId(), individual.getApellido());
                gestionModificarCliente.modDireccionIndividual(individual.getId(), direccion);
                gestionModificarCliente.modCiudadIndividual(individual.getId(), ciudad);
                gestionModificarCliente.modCodigoPostalIndividual(individual.getId(), codigopostal);
                gestionModificarCliente.modPaisIndividual(individual.getId(), pais);

            }else if(cliente.getTipoCliente().equals("Jurídico")){
                gestionModificarCliente.modRazonSocialEmpresa(empresa.getId(), empresa.getRazonSocial());
                gestionModificarCliente.modDireccionEmpresa(empresa.getId(), direccion);
                gestionModificarCliente.modCiudadEmpresa(empresa.getId(), ciudad);
                gestionModificarCliente.modCodigoPostalEmpresa(empresa.getId(), codigopostal);
                gestionModificarCliente.modPaisEmpresa(empresa.getId(), pais);
            }

            return "Listaclientes.xhtml";
        } catch (PersonaNoExisteException e) {
            LOGGER.info("PersonaNoExisteException " + e.getMessage());
        } catch (CampoVacioException e) {
            LOGGER.info("CampoVacioException " + e.getMessage());
        } catch (IndividualNoExistenteException e) {
            LOGGER.info("IndividualNoExistenteException " + e.getMessage());
        } catch (EmpresaNoExistenteException e) {
            LOGGER.info("EmpresaNoExistenteException " + e.getMessage());
        }
        return "Listaclientes.xhtml";
    }


}