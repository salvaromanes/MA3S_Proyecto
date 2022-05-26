package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionModificarPAutorizada;
import ma3s.fintech.ejb.excepciones.*;
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
public class ListaPAutorizadas {
    @Inject
    private Sesion sesion;

    @Inject
    private GestionModificarPAutorizada gestionModificarPAutorizada;

    @Inject
    private GestionGetClientes gestionGetClientes;

    private final static Logger LOGGER = Logger.getLogger(GestionModificarPAutorizada.class.getCanonicalName());

    public List<PAutorizada> getPAutorizadas (){ return gestionGetClientes.getPAutorizadas(); }

    public String modificarPAutorizado (String ident){
        sesion.setIdentificacion(ident);
        return "ModificarPersonasAutorizadas.xhtml?faces-redirect=true";
    }

    public String baja(PAutorizada p){
        try{
            gestionModificarPAutorizada.modificarEstado(sesion.getUsuario().getUser(), p.getId(), "Inactivo");
            return "ListaPAutorizadas.xhtml?faces-redirect=true";
        } catch (EstadoNoValidoException e) {
            LOGGER.info("Estado no valido");
            showMessage("Estado no valido, son: activo, inactivo y bloqueado ");
        } catch (NoEsAdministrativoException e) {
            LOGGER.info("Cuenta Abierta");
            showMessage("Cuenta abierta, no es posible cerrar la cuenta.");
        } catch (PersonaNoExisteException e) {
            LOGGER.info("Persona no existe");
            showMessage("Persona no existe");;
        } catch (CampoVacioException e) {
            LOGGER.info("Campo vacio");
            showMessage("Campo vacio");
        }
        return null;
    }


    public String bloquear(PAutorizada p){
        try{
            gestionModificarPAutorizada.modificarEstado(sesion.getUsuario().getUser(), p.getId(), "Bloqueado");
            return "ListaPAutorizadas.xhtml?faces-redirect=true";
        } catch (EstadoNoValidoException e) {
            LOGGER.info("Estado no valido");
            showMessage("Estado no valido, son: activo, inactivo y bloqueado ");
        } catch (NoEsAdministrativoException e) {
            LOGGER.info("Cuenta Abierta");
            showMessage("Cuenta abierta, no es posible cerrar la cuenta.");
        } catch (PersonaNoExisteException e) {
            LOGGER.info("Persona no existe");
            showMessage("Persona no existe");;
        } catch (CampoVacioException e) {
            LOGGER.info("Campo vacio");
            showMessage("Campo vacio");
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
}
