package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionEliminarAutorizados;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.excepciones.DatosIncorrectosException;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "mostrarDatosCuentaSegregada")
@RequestScoped
public class MostrarDatosCuentaSegregada {
    private static final Logger LOGGER = Logger.getLogger(MostrarDatosCuentaSegregada.class.getCanonicalName());

    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @Inject
    private GestionEliminarAutorizados gestionEliminarAutorizados;

    @Inject
    private Sesion sesion;

    private List<Autorizacion> autorizaciones = new ArrayList<Autorizacion>();
    private List<Segregada> listaSegregada = new ArrayList<Segregada>();
    private String iban;
    private String id;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PostConstruct
    public void mostrarDatosCuentaSegregada(){
        this.iban = sesion.getIban();
        this.id = sesion.getCliente();
    }

    public synchronized List<Autorizacion> getAutorizaciones() {
        return gestionGetCuentas.getAutorizaciones(iban, id);
    }

    //Metodo para leer a todos los clientes
    public synchronized List<Segregada> getCuentas(){
        return gestionGetCuentas.getSegregada(iban);
    }

    public synchronized List<Referencia> getReferencia() {return gestionGetCuentas.getReferenciaSegregada(iban);}

    public synchronized String eliminarAutorizaciones(Long idPaut, Long idEmp){
        try {
            gestionEliminarAutorizados.darBaja(sesion.getUsuario().getUser(), idPaut, idEmp);
            return "Listacuentas.xhtml?faces-redirect";
        } catch (DatosIncorrectosException e) {
            LOGGER.info("Los datos introducidos son incorrectos");
            showMessage("Los datos introducidos son incorrectos");
        } catch (PersonaNoExisteException e) {
            LOGGER.info("La persona no existe");
            showMessage("La persona no existe");
        } catch (NoEsPAutorizadaException e) {
            LOGGER.info("No es persona autorizada");
            showMessage("No es persona autorizada");
        } catch (EmpresaNoExistenteException e) {
            LOGGER.info("La empresa no existe");
            showMessage("La empresa no existe");
        }
        return null;
    }
    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
}
