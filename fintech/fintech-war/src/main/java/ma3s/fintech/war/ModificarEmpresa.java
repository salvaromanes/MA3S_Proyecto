package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.logging.Logger;


@Named(value = "cliente")
@RequestScoped
public class ModificarEmpresa {

    private static final Logger LOGGER = Logger.getLogger(ModificarEmpresa.class.getCanonicalName());

    private String identificacion;
    private String direccion;
    private String ciudad;
    private String codigopostal;
    private String pais;
    private String razonSocial;

    @Inject
    private GestionModificarCliente gestionModificarCliente;
    @Inject
    private GestionGetClientes gestionGetClientes;

    @Inject
    private Sesion sesion;

    private Cliente cliente;

    private Empresa empresa;

    public ModificarEmpresa(){ }


    @PostConstruct
    public void ModificarEmpresa(){
        //individual = new Individual();

        try{
            empresa = gestionGetClientes.devolverEmpresa(sesion.getIdentificacion());
        } catch (EmpresaNoExistenteException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente getCliente(){
        return cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }



    public String modificar() {

        try {

            gestionModificarCliente.modRazonSocialEmpresa(empresa.getId(), empresa.getRazonSocial());
            gestionModificarCliente.modDireccionEmpresa(empresa.getId(), empresa.getDireccion());
            gestionModificarCliente.modCiudadEmpresa(empresa.getId(), empresa.getCiudad());
            gestionModificarCliente.modCodigoPostalEmpresa(empresa.getId(), empresa.getCodigopostal());
            gestionModificarCliente.modPaisEmpresa(empresa.getId(), empresa.getPais());

            return "Listaclientes.xhtml";

        } catch (EmpresaNoExistenteException e) {
            LOGGER.info("EmpresaNoExistenteException " + e.getMessage());
        } catch (CampoVacioException e) {
            LOGGER.info("CampoVacioException " + e.getMessage());
        }
        return "Listaclientes.xhtml";
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}