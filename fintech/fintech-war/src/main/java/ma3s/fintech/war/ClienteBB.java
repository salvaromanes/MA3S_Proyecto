package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.ejb.GestionGetClientes;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class ClienteBB {
    @Inject
    private GestionGetClientes gestionGetClientes;

    @Inject
    private Sesion infosesion;

    private Cliente cliente;

    private final static Logger LOGGER = Logger.getLogger(GestionGetClientes.class.getCanonicalName());

    public ClienteBB(){

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
}
