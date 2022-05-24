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
            showMessage("Cuenta no existe");
        } catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario no encontrado");
            showMessage("Usuario para el proceso no encontrado");
        } catch (CuentaNoVacia cuentaNoVacia) {
            LOGGER.info("Cuenta no vacia");
            showMessage("La cuenta no está vacía");
        } catch (UsuarioIncorrectoException e) {
            LOGGER.info("Usuario Incorrecto");
            showMessage("Usuario para el proceso incorrecto");
        }
        return null;
    }

    public String fechaSimple(Date date){

        if(date == null){
            return null;
        }
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public String verCuentaPooled(String iban, String cliente){
        infosesion.setIban(iban);
        infosesion.setCliente(cliente);
        return "MostrarDatosCuentaPooled.xhtml?faces-redirect=true";
    }

    public String verCuentaSegregada(String iban, String cliente){
        infosesion.setIban(iban);
        infosesion.setCliente(cliente);
        return "MostrarDatosCuentaSegregada.xhtml?faces-redirect=true";
    }
}
