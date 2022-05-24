package ma3s.fintech.war;


import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.GestionAltaCliente;
import ma3s.fintech.ejb.GestionAltaPAutorizada;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteYaExistenteException;
import ma3s.fintech.ejb.excepciones.PautorizadaYaExistenteException;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.logging.Logger;

@Named(value = "DarAltaPA")
@RequestScoped
public class DarAltaPAutorizadaAdmin {

    private static final Logger LOGGER = Logger.getLogger(DarAltaPAutorizadaAdmin.class.getCanonicalName());


    @Inject
    private GestionAltaPAutorizada gestionAltaPAutorizada;

    private PAutorizada pa;

    public DarAltaPAutorizadaAdmin(){
        pa = new PAutorizada();
    }




    public String darAltaPAutorizada() {
        try {
            pa.setEstado("Abierto");
            pa.setFechaInicio(new Date());
            gestionAltaPAutorizada.darAltaPA(pa);

            return "ListaPAutorizadas.xhtml?faces-redirect=true";
        }  catch (CampoVacioException e) {
            LOGGER.info("Campo vacio");
            FacesMessage fm = new FacesMessage("El campo esta vacio");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        } catch (PautorizadaYaExistenteException e) {
            LOGGER.info("pa ya existe");
            FacesMessage fm = new FacesMessage("PA ya existente");
            FacesContext.getCurrentInstance().addMessage("admin: ", fm);
        }
        return null;
    }

    public PAutorizada getPa() {
        return pa;
    }

    public void setPa(PAutorizada pa) {
        this.pa = pa;
    }
}