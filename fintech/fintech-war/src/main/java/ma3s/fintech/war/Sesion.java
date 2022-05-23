package ma3s.fintech.war;

import ma3s.fintech.Pooled;
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

    private Usuario usuario;

    private Pooled pooled;

    private String identificacion;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Pooled getPooled() {
        return pooled;
    }

    public void setPooled(Pooled p){
        pooled = p;
    }

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
            this.setUsuario(gestionAccesoAplicacion.refrescarUsuario(usuario));
            return "principalCliente.xhtml?faces-redirect=true";
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
            this.setUsuario(gestionAccesoAplicacion.refrescarUsuario(usuario));
            return "principalAdmin.xhtml?faces-redirect=true";
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

    public synchronized String invalidarSesionActual() {
        if (usuario != null) {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "login.xhtml";
    }
}
