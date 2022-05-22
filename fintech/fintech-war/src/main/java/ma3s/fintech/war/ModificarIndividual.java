package ma3s.fintech.war;

import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.logging.Logger;


@Named(value = "individual")
@RequestScoped
public class ModificarIndividual {

    private static final Logger LOGGER = Logger.getLogger(ModificarIndividual.class.getCanonicalName());

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

    private Individual individual;


    public ModificarIndividual(){ }


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
        try {
            individual = gestionGetClientes.devolverIndividual(sesion.getIdentificacion());
        } catch (PersonaNoExisteException e) {
            e.printStackTrace();
        }
        identificacion = individual.getIdentificacion();
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }




    public String modificar() {

        try {

            gestionModificarCliente.modNombreIndividual(individual.getId(), nombre);
            gestionModificarCliente.modApellidoIndividual(individual.getId(), apellido);
            gestionModificarCliente.modDireccionIndividual(individual.getId(), direccion);
            gestionModificarCliente.modCiudadIndividual(individual.getId(), ciudad);
            gestionModificarCliente.modCodigoPostalIndividual(individual.getId(), codigopostal);
            gestionModificarCliente.modPaisIndividual(individual.getId(), pais);

            return "Listaclientes.xhtml";

        } catch (CampoVacioException e) {
            LOGGER.info("CampoVacioException " + e.getMessage());
        } catch (IndividualNoExistenteException e) {
            LOGGER.info("IndividualNoExistenteException " + e.getMessage());
        }
        return "Listaclientes.xhtml";
    }

}