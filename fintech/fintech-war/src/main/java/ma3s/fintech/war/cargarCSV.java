package ma3s.fintech.war;

import ma3s.fintech.ejb.GestionGenerarCSV;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.logging.Logger;

public class cargarCSV {
    private static final Logger LOGGER = Logger.getLogger(cargarCSV.class.getCanonicalName());

    @Inject
    private FileDownloadView fileDownloadView;

    @Inject
    private GestionGenerarCSV gestionGenerarCSV;

    @Inject
    public Sesion sesion;

    private int cont = 0;

    public String descargarCSV(){
        try {
            Path origenPath = FileSystems.getDefault().getPath("/opt/jboss/wildfly/docs/Alemania.csv");
            File file = origenPath.toFile();
            FileWriter fr = new FileWriter(file, true);
            if(cont == 0){
                fr.write(gestionGenerarCSV.generarCSV(sesion.getUsuario().getUser(), "Inicial"));
            }else{
                fr.write(gestionGenerarCSV.generarCSV(sesion.getUsuario().getUser(), "Periodico"));
            }
            cont++;
            fr.close();
            return "csv.xhtml?faces-redirect=true";
        } catch (FileNotFoundException e) {
            LOGGER.info("Fichero no encontrado " + e.getMessage());
            showMessage("El fichero no se puede descargar");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (IOException e) {
            LOGGER.info("Fichero no encontrado IO " + e.getMessage());
            showMessage("El fichero no se puede descargar");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (UsuarioNoEncontradoException e) {
            LOGGER.info("Usuario no encontrado " + e.getMessage());
            showMessage("El fichero no se puede descargar");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        } catch (PersonaNoExisteException e) {
            LOGGER.info("Usuario inexistente " + e.getMessage());
            showMessage("El fichero no se puede descargar");
            FacesMessage fm = new FacesMessage("El usuario o la contraseña introducidos son incorrectos");
            FacesContext.getCurrentInstance().addMessage("index:user", fm);
            FacesContext.getCurrentInstance().addMessage("admin:user", fm);
        }
        return "csv.xhtml?faces-redirect=true";
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
}
