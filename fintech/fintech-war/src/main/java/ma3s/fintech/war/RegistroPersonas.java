package ma3s.fintech.war;

// importaciones de los ejb y faces
import ma3s.fintech.ejb.*;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;

// importaciones de excepciones
import ma3s.fintech.ejb.excepciones.*;

// importaciones de entidades jpa
import ma3s.fintech.Usuario;

@Named(value = "registro")
@RequestScoped
public class RegistroPersonas {

// inyectar datos de los ejb

    private Usuario usuario;
    private String password_res;

    private String cuenta;
    private String mensajeValidacion;
    private boolean validacionOK;

    private boolean registroCorrecto;

    public boolean isregistroCorrecto() {
        return registroCorrecto;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public RegistroPersonas(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String RegistrarPer(){
        try{
            if(!usuario.getContrasena().equals(password_res)){
                FacesMessage facesMessage = new FacesMessage("La contraseña pasada no se corresponde");
                return null;
            }
        } catch (UsuarioIncorrectoException e) {
            throw new RuntimeException(e);
        }
        return null;
    }





}
