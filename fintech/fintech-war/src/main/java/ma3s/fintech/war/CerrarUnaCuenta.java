package ma3s.fintech.war;


import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.GestionCierreCuenta;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Named(value = "crearCuenta")
@RequestScoped
public class CerrarUnaCuenta {
    private static final Logger LOGGER = Logger.getLogger(InicioSesionIndex.class.getCanonicalName());

    @Inject
    private GestionCierreCuenta gestionCierreCuenta;

    public CerrarUnaCuenta(){

    }

    public String cerrarCuenta(String IBAN){
        try{
            LOGGER.info("Cerrando cuenta con IBAN: " + IBAN);

            gestionCierreCuenta.cerrarCuenta(IBAN, "usuario");
            return "listaCuentas.xhtml";
        } catch (CuentaNoExistenteException e) {

        } catch (UsuarioNoEncontradoException e) {

        } catch (CuentaNoVacia cuentaNoVacia) {

        } catch (UsuarioIncorrectoException e) {

        }

        return "cerrarCuenta.xhtml";
    }

}
