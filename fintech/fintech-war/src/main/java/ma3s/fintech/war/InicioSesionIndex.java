package ma3s.fintech.war;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAccesoAplicacion;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.ContraseñaIncorrectaException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "InicioSesion")
@RequestScoped
public class InicioSesionIndex {
    private Usuario usuario;

    @Inject
    private GestionAccesoAplicacion gestionAccesoAplicacion;

    public InicioSesionIndex(){ usuario = new Usuario(); }

    public String getUsername() {
        return usuario.getUser();
    }

    public String getPassword() {
        return usuario.getContrasena();
    }

    public String entrar() {
        try {
            gestionAccesoAplicacion.accederAplicacion(usuario.getUser(), usuario.getContrasena());
            return "index.xhtml";
        }catch (UsuarioIncorrectoException e) {
            FacesMessage fm = new FacesMessage("El usuario introducido es incorrecto");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
        }catch (ContraseñaIncorrectaException e) {
            FacesMessage fm = new FacesMessage("La contraseña introducida es incorrecta");
            FacesContext.getCurrentInstance().addMessage("index:pass", fm);
        }catch (AccesoException e) {
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
        }
        return null;
    }
}
