package ma3s.fintech.war;


import ma3s.fintech.Empresa;
import ma3s.fintech.ejb.GestionAltaCliente;
import ma3s.fintech.ejb.GestionBajaCliente;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteNoExisteException;
import ma3s.fintech.ejb.excepciones.ClienteYaExistenteException;
import ma3s.fintech.ejb.excepciones.CuentaAbiertaException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value = "DarAltaEmpresaAdministrador")
@RequestScoped
public class DarAltaEmpresaAdmin {

    @Inject
    private GestionAltaCliente gestionAltaCliente;
    private GestionBajaCliente gestionBajaCliente;
    private Empresa empresa;

    public DarAltaEmpresaAdmin(){
        empresa = new Empresa();
    }


    public String modificar() {
        try {
            gestionAltaCliente.darAltaEmpresa(empresa);
            gestionBajaCliente.darBajaCliente(empresa.getId());

            return "DarAltasEmpresasAdmin.xhtml";
        } catch (ClienteYaExistenteException e) {
            FacesMessage fm = new FacesMessage("El cliente ya existe");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (ClienteNoExisteException e) {
            FacesMessage fm = new FacesMessage("El cliente no existe");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (CuentaAbiertaException e) {
            FacesMessage fm = new FacesMessage("La cuenta está abierta");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (CampoVacioException e) {
            FacesMessage fm = new FacesMessage("El campo esta vacio");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        }
        return null;
    }
}