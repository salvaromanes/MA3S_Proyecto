package ma3s.fintech.war;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAccesoAplicacion;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.CampoVacioException;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named(value = "sesion")
@SessionScoped
public class Sesion implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(Sesion.class.getCanonicalName());

    @Inject
    private GestionAccesoAplicacion gestionAccesoAplicacion;

    @Inject
    private SesionActual sesionActual;

    private Usuario usuario;

    public Sesion(){ usuario = new Usuario(); }

    public synchronized void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario(){
        return usuario;
    }

    public String entrarUsuario() {
        try {
            gestionAccesoAplicacion.entrarAplicacion(usuario.getUser(), usuario.getContrasena());
            sesionActual.setUsuario(gestionAccesoAplicacion.refrescarUsuario(usuario));
            return "principalCliente.xhtml";
        } catch (CampoVacioException e) {
            LOGGER.info("Campos sin rellenar");
            FacesMessage fm = new FacesMessage("Campos sin rellenar");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (AccesoException e) {
            LOGGER.info("Usuario incorrecto");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        }
        return null;
    }

    public String entrarAdmin() {
        try {
            gestionAccesoAplicacion.entrarAplicacionAdministrador(usuario.getUser(), usuario.getContrasena());
            sesionActual.setUsuario(gestionAccesoAplicacion.refrescarUsuario(usuario));
            return "principalAdmin.xhtml";
        } catch (CampoVacioException e) {
            LOGGER.info("Campos sin rellenar");
            FacesMessage fm = new FacesMessage("Campos sin rellenar");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (AccesoException e) {
            LOGGER.info("Usuario incorrecto" + e.getMessage());
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        }
        return null;
    }
}
