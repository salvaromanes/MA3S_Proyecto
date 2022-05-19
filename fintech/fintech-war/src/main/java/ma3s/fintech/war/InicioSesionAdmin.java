package ma3s.fintech.war;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAccesoAplicacion;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.ContraseñaIncorrectaException;
import ma3s.fintech.ejb.excepciones.ErrorInternoException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Named(value = "admin")
@RequestScoped
public class InicioSesionAdmin {
    private static final Logger LOGGER = Logger.getLogger(InicioSesionIndex.class.getCanonicalName());

    @Inject
    private Sesion sesion;

    @Inject
    private GestionAccesoAplicacion gestionAccesoAplicacion;

    private Usuario usuario;

    public InicioSesionAdmin(){ usuario = new Usuario(); }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public String entrar() {
        try {
            LOGGER.info(usuario.getUser());
            sesion.setUsuario(gestionAccesoAplicacion.entrarAplicacion(usuario.getUser(), usuario.getContrasena()));
            return "principalAdmin.xhtml";
        }catch (UsuarioIncorrectoException e) {
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
        }catch (ContraseñaIncorrectaException e) {
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:pass", fm);
        }catch (AccesoException e) {
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (ErrorInternoException e) {
            FacesMessage fm = new FacesMessage("Error interno");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
        }
        return "admin.xhtml";
    }
}
