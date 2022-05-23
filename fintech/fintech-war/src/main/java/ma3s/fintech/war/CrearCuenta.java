package ma3s.fintech.war;


import ma3s.fintech.Divisa;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.excepciones.*;

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

    public String crearCuentaPooled(String user){
        try{
            LOGGER.info("");
            gestionAperturaCuenta.abrirCuentaPooled(IBAN, SWIFT, user, divisa, identificacion);
            return "Listacuentas.xhtml";
        }catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario incorrecto");
            FacesMessage fm = new FacesMessage("El usuario no se encuentra en la base de datos");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaPooled:divisa", fm);
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("No es administrador");
            FacesMessage fm = new FacesMessage("");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaPooled:divisa", fm);
        } catch (CuentaExistenteException e) {
            LOGGER.info("La cuenta ya existe");
            FacesMessage fm = new FacesMessage("La cuenta ya existe");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaPooled:divisa", fm);
        } catch (DivisaExistenteException e) {
            LOGGER.info("La divisa no existe");
            FacesMessage fm = new FacesMessage("La divisa no existe");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaPooled:divisa", fm);
        } catch (ClienteNoExisteException e) {
            LOGGER.info("El cliente no existe");
            FacesMessage fm = new FacesMessage("El cliente no existe");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaPooled:divisa", fm);
        }

        return "CrearCuentaPooled.xhtml";
    }

    public String crearCuentaSegregada(String user){
        try{
            LOGGER.info("");
            gestionAperturaCuenta.abrirCuentaSegregate(IBAN, SWIFT, user, identificacion);
            return "Listacuentas.xhtml";

        }catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario incorrecto");
            FacesMessage fm = new FacesMessage("El usuario no se encuentra en la base de datos");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaSegregada:iban", fm);
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("No es administrador");
            FacesMessage fm = new FacesMessage("");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaSegregada:iban", fm);
        } catch (CuentaExistenteException e) {
            LOGGER.info("La cuenta ya existe");
            FacesMessage fm = new FacesMessage("La cuenta ya existe");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaSegregada:iban", fm);
        } catch (ClienteNoExisteException e) {
            LOGGER.info("El cliente no existe");
            FacesMessage fm = new FacesMessage("El cliente no existe");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaSegregada:iban", fm);
        }

        return "CrearCuentaSegregada.xhtml";
    }

}
