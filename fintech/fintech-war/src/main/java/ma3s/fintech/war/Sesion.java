package ma3s.fintech.war;

import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionAccesoAplicacion;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named(value = "sesion")
@SessionScoped
public class Sesion implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(Sesion.class.getCanonicalName());

    @Inject
    private GestionAccesoAplicacion gestionAccesoAplicacion;

    private Usuario usuario;

    private Pooled pooled;
    private Segregada segregada;
    private String iban_transaccion;

    private String ibanViewTrans;

    private String identificacion;

    private Boolean sesion_iniciada = false;
    private Boolean sesion_admin = false;

    private String iban;
    private String cliente;

    public String getIbanViewTrans() {
        return ibanViewTrans;
    }

    public void setIbanViewTrans(String ibanViewTrans) {
        this.ibanViewTrans = ibanViewTrans;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Pooled getPooled() {
        return pooled;
    }

    public void setPooled(Pooled p){
        pooled = p;
    }

    public Segregada getSegregada() {
        return segregada;
    }

    public void setSegregada(Segregada segregada) {
        this.segregada = segregada;
    }

    public Sesion(){ usuario = new Usuario(); }

    public synchronized void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario(){
        return usuario;
    }

    public String entrarUsuario() {
        try {
            gestionAccesoAplicacion.entrarAplicacion(usuario.getUser(), usuario.getContrasena());
            this.setUsuario(gestionAccesoAplicacion.refrescarUsuario(usuario));
            sesion_iniciada = true;
            return "principalCliente.xhtml?faces-redirect=true";
        } catch (CampoVacioException e) {
            LOGGER.info("Campos sin rellenar " + e.getMessage());
            showMessage("Campos sin rellenar");
            FacesMessage fm = new FacesMessage("Campos sin rellenar");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (AccesoException e) {
            LOGGER.info("Usuario incorrecto " + e.getMessage());
            showMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        }
        return null;
    }

    public String entrarAdmin() {
        try {
            gestionAccesoAplicacion.entrarAplicacionAdministrador(usuario.getUser(), usuario.getContrasena());
            this.setUsuario(gestionAccesoAplicacion.refrescarUsuario(usuario));
            sesion_iniciada = true;
            sesion_admin = true;
            return "principalAdmin.xhtml?faces-redirect=true";
        } catch (CampoVacioException e) {
            LOGGER.info("Campos sin rellenar");
            showMessage("Campos sin rellenar");
            FacesMessage fm = new FacesMessage("Campos sin rellenar");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (AccesoException e) {
            LOGGER.info("Usuario incorrecto" + e.getMessage());
            showMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        }
        return null;
    }

    public synchronized String invalidarSesionActual() {
        if (usuario != null) {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "mainv2.xhtml?faces-redirect=true";
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public String volver(){
        return "mainv2.xhtml?faces-redirect=true";
    }

    public String cerrarSesion(){
        String view = null;

        if (usuario != null && usuario.getEsAdmin()) {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            view = "admin.xhtml?faces-redirect=true";
        }else if(usuario != null && !usuario.getEsAdmin()){
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            view = "index.xhtml?faces-redirect=true";
        }
        sesion_iniciada = false;
        sesion_admin = false;
        return view;
    }

    public String sinInicio(){
        return "mainv2.xhtml?faces-redirect=true";
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Boolean getSesion_iniciada() {
        return sesion_iniciada;
    }

    public Boolean getSesion_admin() {
        return sesion_admin;
    }

    public void setSesion_iniciada(Boolean sesion_iniciada) {
        this.sesion_iniciada = sesion_iniciada;
    }

    public void setSesion_admin(Boolean sesion_admin) {
        this.sesion_admin = sesion_admin;
    }

    public String getIban_transaccion() {
        return iban_transaccion;
    }

    public void setIban_transaccion(String iban_transaccion) {
        this.iban_transaccion = iban_transaccion;
    }
}
