package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.IndividualNoExistenteException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;


@Named(value = "pa")
@RequestScoped
public class ModificarPersonaAutorizada {

    private static final Logger LOGGER = Logger.getLogger(ModificarPersonaAutorizada.class.getCanonicalName());

    private String identificacion;

    private String direccion;
    private String ciudad;
    private String codigopostal;
    private String pais;
    private String nombre;
    private String apellido;

    @Inject
    private GestionModificarCliente gestionModificarCliente;
    @Inject
    private GestionGetClientes gestionGetClientes;

    @Inject
    private Sesion sesion;

    private Cliente cliente;

    private PAutorizada pa;

    public ModificarPersonaAutorizada(){

    }

    @PostConstruct
    public void ModificarIndividual(){
       try{
            pa = gestionGetClientes.devolverPA(sesion.getIdentificacion());
        } catch (EmpresaNoExistenteException e) {
           throw new RuntimeException(e);
       }
    }


    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }

    public PAutorizada getPa() {
        return pa;
    }

    public void setPa(PAutorizada pa) {
        this.pa = pa;
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


    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }




    public String modificar() {

        try {

            gestionModificarCliente.modNombrePA(pa.getId(), pa.getNombre());
            gestionModificarCliente.modApellidosPA(pa.getId(), pa.getApellidos());
            gestionModificarCliente.modDireccionPA(pa.getId(), pa.getDireccion());

            return "Listaclientes.xhtml?faces-redirect=true";

        } catch (CampoVacioException e) {
            LOGGER.info("CampoVacioException " + e.getMessage());
        } catch (PersonaNoExisteException e) {
            throw new RuntimeException(e);
        }
        return "Listaclientes.xhtml";
    }

}