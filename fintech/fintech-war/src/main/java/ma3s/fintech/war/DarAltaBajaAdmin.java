package ma3s.fintech.war;


import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionAltaCliente;
import ma3s.fintech.ejb.GestionBajaCliente;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value = "DarAltaBajaAdmin")
@RequestScoped
public class DarAltaBajaAdmin {

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
    private GestionAltaCliente gestionAltaCliente;
    private GestionBajaCliente gestionBajaCliente;

    private Individual individual;
    private Empresa empresa;

    public DarAltaBajaAdmin(){
        individual = new Individual();
        empresa = new Empresa();
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

    public String getCodigoPostal() {
        return codigopostal;
    }

    public String getPais() {
        return pais;
    }

    public void setId() {
        this.id =  id;
    }

    public void setIdentificacion() {
        this.identificacion =  identificacion;
    }

    public void setTipoCliente() {
        this.tipoCliente =  tipoCliente;
    }

    public void setEstado() {
        this.estado =  estado;
    }

    public void setFechaAlta() {
        this.fechaAlta =  fechaAlta;
    }

    public void setFechaBaja() {
        this.fechaBaja =  fechaBaja;
    }

    public void setDireccion() {
        this.direccion =  direccion;
    }

    public void setCiudad() {
        this.ciudad =  ciudad;
    }

    public void setCodigoPostal() {
        this.codigopostal =  codigopostal;
    }

    public void setPais() {
        this.pais =  pais;
    }

    public String modificar() {
        try {
            if(getTipoCliente().equals("Indivual")){
                gestionAltaCliente.darAltaIndividual(individual);
                gestionBajaCliente.darBajaCliente(id);

            }else if(getTipoCliente().equals("Empresa")){
                gestionAltaCliente.darAltaEmpresa(empresa);
                gestionBajaCliente.darBajaCliente(id);
            }
            return "DarAltaBajaAdmin.xhtml";
        } catch (ClienteYaExistenteException e) {
            FacesMessage fm = new FacesMessage("El cliente ya existe");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (ClienteNoExisteException e) {
            FacesMessage fm = new FacesMessage("El cliente no existe");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (CuentaAbiertaException e) {
            FacesMessage fm = new FacesMessage("La cuenta está abierta");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (CampoVacioException e) {
            FacesMessage fm = new FacesMessage("El campo esta vacio");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        }
        return null;
    }
}