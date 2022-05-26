package ma3s.fintech.war;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.Transaccion;
import ma3s.fintech.ejb.GestionBajaCliente;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionTransferencia;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteNoExisteException;
import ma3s.fintech.ejb.excepciones.CuentaAbiertaException;
import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named("listTrans")
@RequestScoped
public class ListaTransacciones {
    @Inject
    private GestionTransferencia gestionTransferencia;

    private final static Logger LOGGER = Logger.getLogger(GestionGetClientes.class.getCanonicalName());

    public ListaTransacciones(){ }

    public synchronized List<Transaccion> getTransacciones(){ return gestionTransferencia.getAllTransacciones(); }
}
