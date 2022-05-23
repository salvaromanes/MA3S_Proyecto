package ma3s.fintech.war;

import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionCierreCuenta;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.excepciones.CuentaNoExistenteException;
import ma3s.fintech.ejb.excepciones.CuentaNoVacia;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ListaCuentas {
    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @Inject
    private GestionCierreCuenta gestionCierreCuenta;

    @Inject
    private Sesion infosesion;

    private Cuenta cuenta;

    private final static Logger LOGGER = Logger.getLogger(ListaCuentas.class.getCanonicalName());

    public ListaCuentas(){

    }

    public Cuenta getCuenta() {return cuenta;}

    public void setCuenta(Cuenta c) {this.cuenta = c;}

    public synchronized List<Cuenta> getCuentas(){
        return gestionGetCuentas.getCuentas();
    }

    public synchronized List<Segregada> getSegregadas(){ return gestionGetCuentas.getSegregadas();}

    public synchronized List<Pooled> getPooleds(){ return gestionGetCuentas.getPooleds();}

    public synchronized List<Referencia> getReferencias() { return gestionGetCuentas.getReferencias();}

    public String cerrar (String iban){
        try{
            gestionCierreCuenta.cerrarCuenta(iban, infosesion.getUsuario().getUser());
            return "Listacuentas.xhtml?faces-redirect=true";
        } catch (CuentaNoExistenteException e) {
            LOGGER.info("Cuenta no existe");
            showMessage();
        } catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario no encontrado");
            showMessage();
        } catch (CuentaNoVacia cuentaNoVacia) {
            LOGGER.info("Cuenta no vacia");
            showMessage();
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("Usuario Incorrecto");
            showMessage();
        }
        return null;
    }

    public String fechaSimple(Date date){

        if(date == null){
            return null;
        }
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", " No se ha podido cerrar la cuenta");

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}
