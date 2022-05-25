package ma3s.fintech.war;


import ma3s.fintech.Divisa;
import ma3s.fintech.Referencia;
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
    private String ibanReferencia;
    private Referencia referencia;

    public Referencia getReferencia(){return referencia;}
    public void setReferencia(Referencia ref){this.referencia = ref;}


    public String getIBAN(){return IBAN;}
    public String getSWIFT(){return SWIFT;}
    public String getDivisa(){return divisa;}
    public String getIbanReferencia() {
        return ibanReferencia;
    }
    public String getIdentificacion() {
        return identificacion;
    }
    public void setSWIFT(String SWIFT){this.SWIFT = SWIFT;}
    public void setIBAN(String IBAN){this.IBAN = IBAN;}
    public void setDivisa(String divisa){this.divisa = divisa;}
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public void setIbanReferencia(String ibanReferencia) {
        this.ibanReferencia = ibanReferencia;
    }

    public CrearCuenta (){

    }

    public String crearCuentaPooled(){
        try{

            gestionAperturaCuenta.abrirCuentaPooled(IBAN, SWIFT, sesion.getUsuario().getUser(), divisa, identificacion);
            gestionAperturaCuenta.referenciaParaPooled(IBAN, divisa);
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
        } catch (PooledException e) {
            LOGGER.info("Error con la cuenta Pooled");
            showMessage("Error con la cuenta Pooled");
        } catch (DatosIncorrectosException e) {
            LOGGER.info("Los datos recibidos como parametros son incorrectos");
            showMessage("Los datos recibidos como parametros son incorrectos");
        }

        return "CrearCuentaPooled.xhtml?faces-redirect=true";
    }

    public String crearCuentaSegregada(){
        try{
            gestionAperturaCuenta.abrirCuentaSegregate(IBAN, SWIFT, sesion.getUsuario().getUser(), identificacion);
            LOGGER.info("Se intenta crear la referencia para la segregada: " + IBAN + " y la referencia: " + ibanReferencia);
            gestionAperturaCuenta.referenciaParaSegregada(ibanReferencia, IBAN, divisa);

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
        } catch (ReferenciaException e) {
            LOGGER.info("La cuenta referencia ya tiene una relacion con una segregada");
            showMessage("La cuenta referencia ya tiene una relacion con una segregada");
        } catch (SegregadaException e) {
            LOGGER.info("La cuenta segregada no existe");
            showMessage("La cuenta segregada no existe");
        } catch (DivisaExistenteException e) {
            LOGGER.info("La divisa no existe");
            showMessage("La divisa no existe");
        } catch (DatosIncorrectosException e) {
            LOGGER.info("Los datos recibidos como parametros son incorrectos");
            showMessage("Los datos recibidos como parametros son incorrectos");
        }

        return "CrearCuentaSegregada.xhtml?faces-redirect=true";
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}
