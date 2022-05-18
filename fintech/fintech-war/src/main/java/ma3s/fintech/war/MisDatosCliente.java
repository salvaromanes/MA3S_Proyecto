package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value = "cliente")
@RequestScoped
public class MisDatosCliente {

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

    private Cliente cliente;

    public MisDatosCliente(){ cliente = new Cliente(); }

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
            if(getTipoCliente().equals("Individual")){
                gestionModificarCliente.modDireccionIndividual(id, direccion);
                gestionModificarCliente.modCiudadIndividual(id, ciudad);
                gestionModificarCliente.modCodigoPostalIndividual(id, codigopostal);
                gestionModificarCliente.modPaisIndividual(id, pais);

            }else if(getTipoCliente().equals("Empresa")){
                gestionModificarCliente.modDireccionEmpresa(id, direccion);
                gestionModificarCliente.modCiudadEmpresa(id, ciudad);
                gestionModificarCliente.modCodigoPostalEmpresa(id, codigopostal);
                gestionModificarCliente.modPaisEmpresa(id, pais);
            }
            return "MisDatosCliente.xhtml";
        } catch (CampoVacioException e) {

        } catch (IndividualNoExistenteException e) {

        } catch (EmpresaNoExistenteException e) {

        }
        return null;
    }
}