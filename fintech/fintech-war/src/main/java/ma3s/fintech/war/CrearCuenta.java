package ma3s.fintech.war;


import ma3s.fintech.Divisa;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.excepciones.CuentaExistenteException;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

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

    public String getIBAN(){return IBAN;}
    public String getSWIFT(){return SWIFT;}
    public String getDivisa(){return divisa;}
    public void setSWIFT(String SWIFT){this.SWIFT = SWIFT;}
    public void setIBAN(String IBAN){this.IBAN = IBAN;}
    public void setDivisa(String divisa){this.divisa = divisa;}

    public CrearCuenta (){

    }

    public String crearCuentaPooled(){
        try{
            LOGGER.info("");
            String nombreUsuario = sesion.getUsuario().getUser();
            gestionAperturaCuenta.abrirCuentaPooled(IBAN, SWIFT, "salva", divisa);
            return "admin.xhtml";
        }catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario incorrecto");
            FacesMessage fm = new FacesMessage("El usuario no se encuentra en la base de datos");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaPooled:divisa", fm);
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("No es administrador");
            FacesMessage fm = new FacesMessage("");
            FacesContext.getCurrentInstance().addMessage("crearCuentaPooled:divisa", fm);
        } catch (CuentaExistenteException e) {
            LOGGER.info("La cuenta ya existe");
            e.printStackTrace();
        } catch (DivisaExistenteException e) {
            LOGGER.info("La divisa no existe");
            FacesMessage fm = new FacesMessage("La divisa no existe");
            FacesContext.getCurrentInstance().addMessage("crearCuentaPooled:divisa", fm);
        }

        return "CrearCuentaPooled.xhtml";
    }

    public String crearCuentaSegregada(){
        try{
            LOGGER.info("");
            String nombreUsuario = sesion.getUsuario().getUser();
            gestionAperturaCuenta.abrirCuentaPooled(IBAN, SWIFT, "salva", divisa);
            return "admin.xhtml";

        }catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario incorrecto");
            FacesMessage fm = new FacesMessage("El usuario no se encuentra en la base de datos");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaSegregada:divisa", fm);
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("No es administrador");
            FacesMessage fm = new FacesMessage("");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaSegregada:divisa", fm);
        } catch (CuentaExistenteException e) {
            LOGGER.info("La cuenta ya existe");
            e.printStackTrace();
        } catch (DivisaExistenteException e) {
            LOGGER.info("La divisa no existe");
            FacesMessage fm = new FacesMessage("La divisa no existe");
            FacesContext.getCurrentInstance().addMessage("CrearCuentaSegregada:divisa", fm);
        }

        return "CrearCuentaSegregada.xhtml";
    }

}
