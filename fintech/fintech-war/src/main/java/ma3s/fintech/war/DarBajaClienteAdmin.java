package ma3s.fintech.war;

import ma3s.fintech.Autorizacion;
import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionBajaCliente;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named(value = "admin")
@RequestScoped
public class DarBajaClienteAdmin {
/*
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
*/
    @Inject
    private GestionBajaCliente gestionBajaCliente;

    @Inject
    private GestionGetClientes gestionGetClientes;

    public DarBajaClienteAdmin(){ }

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

            darBajaCliente(cliente);
            return "ModificarIndividuales.xhtml";
        } catch (ClienteNoExisteException e) {

        }
        return null;
    }

    public String darBajaCliente(Cliente c) {
        try {
            gestionBajaCliente.darBajaCliente(c.getId());

            return "ModificarIndividuales.xhtml";

        } catch (ClienteNoExisteException e) {
            throw new RuntimeException(e);
        } catch (CuentaAbiertaException e) {
            throw new RuntimeException(e);
        } catch (CampoVacioException e) {
            throw new RuntimeException(e);
        }
    }


}