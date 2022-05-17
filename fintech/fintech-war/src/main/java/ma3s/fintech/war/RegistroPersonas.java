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




}
