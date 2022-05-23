package ma3s.fintech.war;

import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarCliente;
import ma3s.fintech.ejb.excepciones.*;

import javax.annotation.PostConstruct;
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

    public ModificarIndividual(){

    }

    @PostConstruct
    public void ModificarIndividual(){
        //individual = new Individual();

        try{
            individual = gestionGetClientes.devolverIndividual(sesion.getIdentificacion());
        } catch (PersonaNoExisteException e) {
            throw new RuntimeException(e);
        }
    }


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
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }




    public String modificar() {

        try {

            gestionModificarCliente.modNombreIndividual(individual.getId(), individual.getNombre());
            gestionModificarCliente.modApellidoIndividual(individual.getId(), individual.getApellido());
            gestionModificarCliente.modDireccionIndividual(individual.getId(), individual.getDireccion());
            gestionModificarCliente.modCiudadIndividual(individual.getId(), individual.getCiudad());
            gestionModificarCliente.modCodigoPostalIndividual(individual.getId(), individual.getCodigopostal());
            gestionModificarCliente.modPaisIndividual(individual.getId(), individual.getPais());

            return "Listaclientes.xhtml?faces-redirect=true";

        } catch (CampoVacioException e) {
            LOGGER.info("CampoVacioException " + e.getMessage());
        } catch (IndividualNoExistenteException e) {
            LOGGER.info("IndividualNoExistenteException " + e.getMessage());
        }
        return "Listaclientes.xhtml";
    }

}