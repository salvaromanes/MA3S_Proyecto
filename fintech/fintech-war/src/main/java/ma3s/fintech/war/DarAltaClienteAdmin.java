package ma3s.fintech.war;


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
import java.util.logging.Logger;

@Named(value = "DarAltaClienteAdministrador")
@RequestScoped
public class DarAltaClienteAdmin {

    private static final Logger LOGGER = Logger.getLogger(DarAltaClienteAdmin.class.getCanonicalName());


    @Inject
    private GestionAltaCliente gestionAltaCliente;

    private Individual individual;

    public DarAltaClienteAdmin(){
        individual = new Individual();
    }


    public String darAltaIndividual() {
        try {
            individual.setTipoCliente("Individual");
            individual.setEstado("Abierto");
            individual.setFechaAlta(new Date());
            gestionAltaCliente.darAltaIndividual(individual);

            return "Listaclientes.xhtml?faces-redirect=true";
        } catch (ClienteYaExistenteException e) {
            LOGGER.info("Cliente Ya Existente");
            FacesMessage fm = new FacesMessage("El cliente ya existe");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (CampoVacioException e) {
            LOGGER.info("Campo Vacio");
            FacesMessage fm = new FacesMessage("El campo esta vacio");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        }
        return null;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }
}