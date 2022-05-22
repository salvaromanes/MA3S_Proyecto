package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.*;

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

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }



    public String modificar() {

        try {

            gestionModificarCliente.modRazonSocialEmpresa(empresa.getId(), razonSocial);
            gestionModificarCliente.modDireccionEmpresa(empresa.getId(), direccion);
            gestionModificarCliente.modCiudadEmpresa(empresa.getId(), ciudad);
            gestionModificarCliente.modCodigoPostalEmpresa(empresa.getId(), codigopostal);
            gestionModificarCliente.modPaisEmpresa(empresa.getId(), pais);

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
        try {
            empresa = gestionGetClientes.devolverEmpresa(sesion.getIdentificacion());
        } catch (EmpresaNoExistenteException e) {
            e.printStackTrace();
        }
        identificacion = empresa.getIdentificacion();
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}