package ma3s.fintech.war;

// importaciones de los ejb y faces
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

// importaciones de excepciones
import  ma3s.fintech.ejb.excepciones.AccesoException;

// importaciones de entidades jpa
import ma3s.fintech.Usuario;

@Named(value = "registro")
@RequestScoped
public class RegistroPersona {

// inyectar datos de los ejb

    private Usuario usuario;
    private String password_res;

    private  String email;

    private String pais;

    private  String direccion;

    private  String ciudad;

    private  String codPostal;

    private String cuenta;
    private String mensajeValidacion;
    private boolean validacionOK;

    private boolean registroCorrecto;

    public boolean isregistroCorrecto() {
        return registroCorrecto;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public String getPassword_res() {
        return password_res;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPassword_res(String password_res) {
        this.password_res = password_res;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }


    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

