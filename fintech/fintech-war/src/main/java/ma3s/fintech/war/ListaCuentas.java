package ma3s.fintech.war;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Pooled;
import ma3s.fintech.ejb.GestionCierreCuenta;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.excepciones.CuentaNoExistenteException;
import ma3s.fintech.ejb.excepciones.CuentaNoVacia;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    private final static Logger LOGGER = Logger.getLogger(GestionGetCuentas.class.getCanonicalName());

    public ListaCuentas(){

    }

    public Cuenta getCuenta() {return cuenta;}

    public void setCuenta(Cuenta c) {this.cuenta = c;}

    public synchronized List<Cuenta> getCuentas(){
        return gestionGetCuentas.getCuentas();
    }

    public String cerrar (String iban){
        try{
            gestionCierreCuenta.cerrarCuenta(iban, infosesion.getUsuario().getUser());
        } catch (CuentaNoExistenteException e) {
            e.printStackTrace();
        } catch (UsuarioNoEncontradoException e) {
            e.printStackTrace();
        } catch (CuentaNoVacia cuentaNoVacia) {
            cuentaNoVacia.printStackTrace();
        } catch (UsuarioIncorrectoException e) {
            e.printStackTrace();
        }
        return null;
    }

}
