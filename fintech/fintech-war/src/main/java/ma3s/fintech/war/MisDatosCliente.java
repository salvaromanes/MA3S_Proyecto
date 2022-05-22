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

    private String identificacion;
    private String estado;
    private Date fechaBaja;
    private String direccion;
    private String ciudad;
    private String codigopostal;
    private String pais;
    private String nombre;
    private String apellido;
    private String razonSocial;

    @Inject
    private GestionModificarCliente gestionModificarCliente;
    @Inject
    private GestionGetClientes gestionGetClientes;

    @Inject
    private Sesion sesion;

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

    public String modificar() {
        String identificacion1 = sesion.getIdentificacion();
        try {
            Cliente c = gestionGetClientes.devolverCliente(identificacion1);
            individual = gestionGetClientes.devolverIndividual(c.getId());
            empresa = gestionGetClientes.devolverEmpresa(c.getId());

            if(c.getTipoCliente().equals("Individual")){
                gestionModificarCliente.modNombreIndividual(individual.getId(), nombre);
                gestionModificarCliente.modApellidoIndividual(individual.getId(), apellido);
                gestionModificarCliente.modDireccionIndividual(individual.getId(), direccion);
                gestionModificarCliente.modCiudadIndividual(individual.getId(), ciudad);
                gestionModificarCliente.modCodigoPostalIndividual(individual.getId(), codigopostal);
                gestionModificarCliente.modPaisIndividual(individual.getId(), pais);

            }else if(cliente.getTipoCliente().equals("Jurídico")){
                gestionModificarCliente.modRazonSocialEmpresa(empresa.getId(), razonSocial);
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
        } catch (ClienteNoExisteException e) {
            LOGGER.info("ClienteNoExisteException " + e.getMessage());
        }
        return "Listaclientes.xhtml";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getIdentificacion() {
        try {
            cliente = gestionGetClientes.devolverCliente(sesion.getIdentificacion());
        } catch (ClienteNoExisteException e) {
            e.printStackTrace();
        }
        identificacion = cliente.getIdentificacion();
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}