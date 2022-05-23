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
import java.util.logging.Logger;

@Named(value = "DarAltaEmpresaAdministrador")
@RequestScoped
public class DarAltaEmpresaAdmin {

    private static final Logger LOGGER = Logger.getLogger(DarAltaClienteAdmin.class.getCanonicalName());


    @Inject
    private GestionAltaCliente gestionAltaCliente;

    private Empresa empresa;

    public DarAltaEmpresaAdmin(){
        empresa = new Empresa();
    }


    public String darAltaEmpresa() {
        try {
            empresa.setTipoCliente("Jurídico");
            empresa.setEstado("Abierto");
            empresa.setFechaAlta(new Date());
            gestionAltaCliente.darAltaEmpresa(empresa);

            return "DarAltasEmpresasAdmin.xhtml";
        } catch (ClienteYaExistenteException e) {
            FacesMessage fm = new FacesMessage("El cliente ya existe");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (CampoVacioException e) {
            FacesMessage fm = new FacesMessage("El campo esta vacio");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        }
        return null;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}