package ma3s.fintech.war;


import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.excepciones.CuentaExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "crearCuenta")
@RequestScoped
public class CrearCuenta {

    @Inject
    private GestionAperturaCuenta gestionAperturaCuenta;

    private String IBAN;
    private String SWIFT;
    private String usuario;

    public String setIBAN(){return IBAN;}
    public String setSWIFT(){return SWIFT;}
    public String usuario(){return usuario;}
    public void setUsuario(String usuario){this.usuario = usuario;}
    public void setSWIFT(String SWIFT){this.SWIFT = SWIFT;}
    public void setIBAN(String IBAN){this.IBAN = IBAN;}

    public String crearCuentaPooled(){
        try{
            gestionAperturaCuenta.abrirCuentaPooled(IBAN, SWIFT, usuario);

        }catch (UsuarioNoEncontradoException e) {
            FacesMessage fm = new FacesMessage("El usuario no se encuentra en la base de datos");
            FacesContext.getCurrentInstance().addMessage("Error", fm);
        } catch (UsuarioIncorrectoException e) {
            e.printStackTrace();
        } catch (CuentaExistenteException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String crearCuentaSegregada(){

        return "index.html";
    }

}
