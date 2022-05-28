package ma3s.fintech.war;

import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.GestionAnadirAutorizados;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.excepciones.*;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Named(value = "addAutorizado")
@RequestScoped
public class AnadirAutorizadoCuenta {
    private final static Logger LOGGER = Logger.getLogger(AnadirAutorizadoCuenta.class.getCanonicalName());
    @Inject
    private GestionAnadirAutorizados gestionAnadirAutorizados;

    @Inject
    private Sesion sesion;

    @Inject
    private GestionGetClientes gestionGetClientes;

    private String iden_pAutorizada;

    private String operacion;

    public String anadirAutorizado(){
        try {
            Empresa e = gestionGetClientes.devolverEmpresa(sesion.getIdentificacion());
            PAutorizada p = gestionGetClientes.getPAutorizada(iden_pAutorizada);
            gestionAnadirAutorizados.anadirPAut(p, e, sesion.getUsuario().getUser(), operacion);
            return "Listaclientes.xhtml?faces-redirect=true";
        } catch (EmpresaNoExistenteException e) {
            LOGGER.info("EmpresaNoExistenteException");
            showMessage("EmpresaNoExistenteException");
        } catch (UsuarioNoEncontradoException e) {
            LOGGER.info("UsuarioNoEncontradoException");
            showMessage("UsuarioNoEncontradoException");
        } catch (EmpresaNoRelacException e) {
            LOGGER.info("EmpresaNoRelacException");
            showMessage("EmpresaNoRelacException");
        } catch (NoEsAdministrativoException e) {
            LOGGER.info("NoEsAdministrativoException");
            showMessage("NoEsAdministrativoException");
        } catch (PersonaNoExisteException e) {
            LOGGER.info("PersonaNoExisteException");
            showMessage("PersonaNoExisteException");
        } catch (NoEsPAutorizadaException e) {
            LOGGER.info("NoEsPAutorizadaException");
            showMessage("NoEsPAutorizadaException");
        } catch (OperacionNoValida operacionNoValida) {
            operacionNoValida.printStackTrace();
        }
        return null;
    }


    public String getIden_pAutorizada() {
        return iden_pAutorizada;
    }

    public void setIden_pAutorizada(String iden_pAutorizada) {
        this.iden_pAutorizada = iden_pAutorizada;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
}
