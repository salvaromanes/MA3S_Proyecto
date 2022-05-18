package ma3s.fintech.war;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.AccesoAplicacion;
import ma3s.fintech.ejb.GestionAccesoAplicacion;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.ContraseñaIncorrectaException;
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
        }catch (UsuarioIncorrectoException e) {
            FacesMessage fm = new FacesMessage("El usuario introducido es incorrecto");
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        }catch (ContraseñaIncorrectaException e) {
            FacesMessage fm = new FacesMessage("La contraseña introducida es incorrecta");
            FacesContext.getCurrentInstance().addMessage("admin:pass", fm);
        }catch (AccesoException e) {
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        }
        return "principalAdmin.xhtml";
    }
}
