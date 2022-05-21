package ma3s.fintech.war;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAccesoAplicacion;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "sesionActual")
@SessionScoped
public class SesionActual implements Serializable {
    @Inject
    private GestionAccesoAplicacion gestionAccesoAplicacion;

    private Usuario usuario;

    public SesionActual() { }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }

    public synchronized String invalidarSesionActual() {
        if (usuario != null) {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "login.xhtml";
    }
}
