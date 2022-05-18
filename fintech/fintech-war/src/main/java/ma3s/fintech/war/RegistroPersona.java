package ma3s.fintech.war;

// importaciones de los ejb y faces
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

// importaciones de excepciones
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionAltaCliente;
import ma3s.fintech.ejb.GestionOperacionUsuario;
import ma3s.fintech.ejb.excepciones.*;

// importaciones de entidades jpa
import ma3s.fintech.Usuario;

@Named(value = "registro")
@RequestScoped
public class RegistroPersona {

    @Inject
    private GestionOperacionUsuario gestionOperacionUsuario;
    private GestionAltaCliente gestionAltaCliente;

    private Usuario usuario;
    private String password_res;

    private  String email;

    private String nombre;

    private  String apellidos;
    private String pais;

    private  String direccion;

    private  String ciudad;

    private  String codPostal;


    public String getApellidos() {
        return apellidos;
    }
    public String getNombre() {
        return nombre;
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
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getPais() {
        return pais;
    }

    public String resgistrarPersona(){
        try{
            if(!usuario.getContrasena().equals(password_res)){
                FacesMessage fm = new FacesMessage("Las contraseñas deben coincidir");
                FacesContext.getCurrentInstance().addMessage("registro:password_res",fm);
                return null;
            }
            Individual individual = new Individual();
            gestionOperacionUsuario.crearUsuario(usuario);
            gestionAltaCliente.darAltaIndividual(individual);
            gestionOperacionUsuario.asignarCliente(individual,usuario.getUser());

        }catch( UsuarioExistenteException e){
            throw new RuntimeException(e);
        } catch (ClienteYaExistenteException e) {
            throw new RuntimeException(e);
        } catch (CampoVacioException e) {
            throw new RuntimeException(e);
        } catch (UsuarioNoEncontradoException e) {
            throw new RuntimeException(e);
        } catch (PersonaNoExisteException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}

