package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.PAutorizada;
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

    // hola

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

    public MisDatosCliente(){ }

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
    }

    public String modificar(String identificacion) {
        try {
            Cliente cliente = new Cliente();
            cliente = gestionModificarCliente.devolverCliente(identificacion);
            if(cliente.getTipoCliente().equals("Individual")){
                gestionModificarCliente.modDireccionIndividual(cliente.getId(), direccion);
                gestionModificarCliente.modCiudadIndividual(cliente.getId(), ciudad);
                gestionModificarCliente.modCodigoPostalIndividual(cliente.getId(), codigopostal);
                gestionModificarCliente.modPaisIndividual(cliente.getId(), pais);

            }else if(cliente.getTipoCliente().equals("Empresa")){
                gestionModificarCliente.modDireccionEmpresa(cliente.getId(), direccion);
                gestionModificarCliente.modCiudadEmpresa(cliente.getId(), ciudad);
                gestionModificarCliente.modCodigoPostalEmpresa(cliente.getId(), codigopostal);
                gestionModificarCliente.modPaisEmpresa(cliente.getId(), pais);
            }
            return "MisDatosCliente.xhtml";
        } catch (CampoVacioException e) {

        } catch (IndividualNoExistenteException e) {

        } catch (EmpresaNoExistenteException e) {

        } catch (ClienteNoExisteException e) {

        }
        return null;
    }
}