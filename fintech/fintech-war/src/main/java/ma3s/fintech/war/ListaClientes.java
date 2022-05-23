package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.GestionBajaCliente;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteNoExisteException;
import ma3s.fintech.ejb.excepciones.CuentaAbiertaException;
import org.primefaces.model.FilterMeta;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ListaClientes {
    @Inject
    private GestionGetClientes gestionGetClientes;

    @Inject
    private GestionBajaCliente bajaCliente;

    @Inject
    private Sesion infosesion;

    private Cliente cliente;


    private final static Logger LOGGER = Logger.getLogger(GestionGetClientes.class.getCanonicalName());

    public ListaClientes(){

    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }


    //Metodo para leer a todos los clientes
    public synchronized List<Cliente> getClientes(){
        return gestionGetClientes.getClientes();
    }

    public synchronized List<Individual> getIndividuales(){ return gestionGetClientes.getIndividuales();}

    public synchronized List<Empresa> getEmpresas(){ return gestionGetClientes.getEmpresas();}

    public String modificar(Cliente c){
        infosesion.setIdentificacion(c.getIdentificacion());
        return "ModificarIndividuales.xhtml?faces-redirect=true";
    }

    public String modificarIndividual(Individual i){
        infosesion.setIdentificacion(i.getIdentificacion());
        return "ModificarIndividuales.xhtml?faces-redirect=true";
    }

    public String modificarEmpresa(Empresa e){
        infosesion.setIdentificacion(e.getIdentificacion());
        return "ModificarEmpresa.xhtml?faces-redirect=true";
    }

    public String baja(Cliente c){
        try{
            bajaCliente.darBajaCliente(c.getId());
            return "Listaclientes.xhtml?faces-redirect=true";
        } catch (ClienteNoExisteException e) {
            e.printStackTrace();
        } catch (CuentaAbiertaException e) {
            e.printStackTrace();
        } catch (CampoVacioException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String fechaSimple(Date date){

        if(date == null){
            return null;
        }
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

}
