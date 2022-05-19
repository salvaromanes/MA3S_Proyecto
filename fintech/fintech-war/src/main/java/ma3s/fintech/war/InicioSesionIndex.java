package ma3s.fintech.war;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAccesoAplicacion;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Named(value = "index")
@RequestScoped
public class InicioSesionIndex {
    private static final Logger LOGGER = Logger.getLogger(InicioSesionIndex.class.getCanonicalName());

    @Inject
    private Sesion sesion;

    @Inject
    private GestionAccesoAplicacion gestionAccesoAplicacion;

    private Usuario usuario;

    public InicioSesionIndex(){ usuario = new Usuario(); }

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
            return "principalCliente.xhtml";
        }catch (UsuarioIncorrectoException e) {
            LOGGER.info("Usuario incorrecto");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
        }catch (ContraseñaIncorrectaException e) {
            LOGGER.info("Contraseña incorrecto");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:pass", fm);
        }catch (AccesoException e) {
            LOGGER.info("Acceso incorrecto");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
        } catch (ErrorInternoException e) {
            FacesMessage fm = new FacesMessage("Error interno");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
        } catch (CampoVacioException e) {
            FacesMessage fm = new FacesMessage("Contraseña vacia");
            FacesContext.getCurrentInstance().addMessage("admin:pass", fm);
        }
        return "index.xhtml";
    }
}
