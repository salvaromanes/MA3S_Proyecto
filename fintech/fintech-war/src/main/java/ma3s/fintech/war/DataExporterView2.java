package ma3s.fintech.war;

import ma3s.fintech.Fintech;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.excepciones.DatosIncorrectosException;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "dataExporterView2")
@ViewScoped
public class DataExporterView2 implements Serializable {

    private List<Fintech> cuentas;

    private Exporter<DataTable> textExporter;

    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @Inject
    private GestionGetClientes gestionGetClientes;

    @PostConstruct
    public void init() {
        cuentas = gestionGetCuentas.getFintechSemanal();
        textExporter = new TextExporter();
    }

    public List<Fintech> getCuentas() {
        return cuentas;
    }
    public void setCuentas(List<Fintech> cuentas) {
        this.cuentas = cuentas;
    }

    public Exporter<DataTable> getTextExporter() {
        return textExporter;
    }

    public void setTextExporter(Exporter<DataTable> textExporter) {
        this.textExporter = textExporter;
    }

    public String getNombre(Long id){
        String nombre = null;
        try {
            nombre = gestionGetClientes.getNombre(id);
        } catch (DatosIncorrectosException e) {
            showMessage("Datos incorrectos");
        }
        return nombre;
    }

    public String getApellidos(Long id){
        String apellidos;
        apellidos = gestionGetClientes.getApellidos(id);
        return apellidos;
    }

    public String getFechaNac(Long id){
        String fecha;
        fecha = gestionGetClientes.getFechNac(id);
        return fecha;
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}
