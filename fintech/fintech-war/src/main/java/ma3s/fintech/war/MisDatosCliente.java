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

@Named(value = "cliente")
@RequestScoped
public class MisDatosCliente {

    private String razonSocial;
    private List<Autorizacion> autorizaciones;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Long id;
    private String identificacion;
    private String tipoCliente;
    private String estado;
    private Date fechaAlta;
    private Date fechaBaja;
    private String direccion;
    private String ciudad;
    private String codigopostal;
    private String pais;

    @Inject
    private GestionModificarCliente gestionModificarCliente;
    @Inject
    private GestionGetClientes gestionGetClientes;

    public MisDatosCliente(){ }

    private Cliente cliente;


/*
    public String getRazonSocial() {
        return razonSocial;
    }

    public List<Autorizacion> getAutorizaciones() {
        return autorizaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public String getPais() {
        return pais;
    }*/


    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }

/*
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setAutorizaciones(List<Autorizacion> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setId(Long id) {
        this.id =  id;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion =  identificacion;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente =  tipoCliente;
    }

    public void setEstado(String estado) {
        this.estado =  estado;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta =  fechaAlta;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja =  fechaBaja;
    }

    public void setDireccion(String direccion) {
        this.direccion =  direccion;
    }

    public void setCiudad(String ciudad) {
        this.ciudad =  ciudad;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal =  codigopostal;
    }

    public void setPais(String pais) {
        this.pais =  pais;
    }*/


    public String pasarCliente(String identificacion) {
        try {
            cliente = gestionGetClientes.devolverCliente(identificacion);

            return "MisDatosClientes.xhtml";
        } catch (ClienteNoExisteException e) {

        }
        return null;
    }

    public String modificar() {
        try {
            Individual individual;
            individual = gestionGetClientes.devolverIndividual(cliente.getId());

            Empresa empresa;
            empresa = gestionGetClientes.devolverEmpresa(cliente.getId());

            if(cliente.getTipoCliente().equals("Individual")){
                gestionModificarCliente.modNombreIndividual(individual.getId(), nombre);
                gestionModificarCliente.modApellidoIndividual(individual.getId(), apellido);
                gestionModificarCliente.modDireccionIndividual(cliente.getId(), direccion);
                gestionModificarCliente.modCiudadIndividual(cliente.getId(), ciudad);
                gestionModificarCliente.modCodigoPostalIndividual(cliente.getId(), codigopostal);
                gestionModificarCliente.modPaisIndividual(cliente.getId(), pais);

            }else if(cliente.getTipoCliente().equals("Empresa")){
                gestionModificarCliente.modRazonSocialEmpresa(empresa.getId(), razonSocial);
                gestionModificarCliente.modDireccionEmpresa(cliente.getId(), direccion);
                gestionModificarCliente.modCiudadEmpresa(cliente.getId(), ciudad);
                gestionModificarCliente.modCodigoPostalEmpresa(cliente.getId(), codigopostal);
                gestionModificarCliente.modPaisEmpresa(cliente.getId(), pais);
            }

            return "MisDatosClientes.xhtml";
        } catch (PersonaNoExisteException e) {

        } catch (CampoVacioException e) {

        } catch (IndividualNoExistenteException e) {

        } catch (EmpresaNoExistenteException e) {

        }
        return null;
    }


}