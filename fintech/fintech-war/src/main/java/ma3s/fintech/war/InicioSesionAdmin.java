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

@Named(value = "InicioSesionAdmin")
@RequestScoped
public class InicioSesionAdmin {
    private String username;
    private String password;

    @Inject
    private GestionAccesoAplicacion gestionAccesoAplicacion;

    private Usuario usuario;

    public InicioSesionAdmin(){ usuario = new Usuario(); }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String entrar() {
        try {
            gestionAccesoAplicacion.accederAplicacion(username, password);
            return "admin.xhtml";
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
        return null;
    }
}
