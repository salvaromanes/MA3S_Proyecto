package ma3s.fintech.war;


import ma3s.fintech.Divisa;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.excepciones.*;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "crearCuenta")
@RequestScoped
public class CrearCuenta {
    private static final Logger LOGGER = Logger.getLogger(CrearCuenta.class.getCanonicalName());

    @Inject
    private GestionAperturaCuenta gestionAperturaCuenta;

    @Inject
    private Sesion sesion;

    private String IBAN;
    private String SWIFT;
    //private List<String> divisa;
    private String divisa;
    private String identificacion;

    public String getIBAN(){return IBAN;}
    public String getSWIFT(){return SWIFT;}
    public String getDivisa(){return divisa;}
    public String getIdentificacion() {
        return identificacion;
    }
    public void setSWIFT(String SWIFT){this.SWIFT = SWIFT;}
    public void setIBAN(String IBAN){this.IBAN = IBAN;}
    public void setDivisa(String divisa){this.divisa = divisa;}
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public CrearCuenta (){

    }

    public String crearCuentaPooled(){
        try{
            LOGGER.info("");
            gestionAperturaCuenta.abrirCuentaPooled(IBAN, SWIFT, sesion.getUsuario().getUser(), divisa, identificacion);
            return "Listacuentas.xhtml?faces-redirect=true";
        }catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario incorrecto");
            showMessage("El usuario no existe");
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("No es administrador");
            FacesMessage fm = new FacesMessage("");
            showMessage("El usuario no puede crear la cuenta porque no es administrador");
        } catch (CuentaExistenteException e) {
            LOGGER.info("La cuenta ya existe");
            showMessage("La cuenta ya existe");
        } catch (DivisaExistenteException e) {
            LOGGER.info("La divisa no existe");
            showMessage("La divisa no existe");
        } catch (ClienteNoExisteException e) {
            LOGGER.info("El cliente no existe");
            showMessage("El cliente no existe");
        }

        return "CrearCuentaPooled.xhtml";
    }

    public String crearCuentaSegregada(){
        try{
            LOGGER.info("");
            gestionAperturaCuenta.abrirCuentaSegregate(IBAN, SWIFT, sesion.getUsuario().getUser(), identificacion);
            return "Listacuentas.xhtmlfaces-redirect=true";

        }catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario incorrecto");
            showMessage("El usuario no puede crear una cuenta porque no es administrador");
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("No es administrador");
            showMessage("El usuario no puede crear una cuenta porque no es administrador");
        } catch (CuentaExistenteException e) {
            LOGGER.info("La cuenta ya existe");
            showMessage("La cuenta ya existe");
        } catch (ClienteNoExisteException e) {
            LOGGER.info("El cliente no existe");
            showMessage("El cliente no existe");
        }

        return "CrearCuentaSegregada.xhtml";
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}
