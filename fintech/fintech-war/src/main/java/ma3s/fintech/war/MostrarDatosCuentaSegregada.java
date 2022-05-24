package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionEliminarAutorizados;
import ma3s.fintech.ejb.GestionGetCuentas;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "mostrarDatosCuentaSegregada")
@RequestScoped
public class MostrarDatosCuentaSegregada {
    private static final Logger LOGGER = Logger.getLogger(MostrarDatosCuentaSegregada.class.getCanonicalName());

    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @Inject
    private GestionEliminarAutorizados gestionEliminarAutorizados;

    @Inject
    private Sesion sesion;

    private List<Autorizacion> autorizaciones = new ArrayList<Autorizacion>();
    private List<Segregada> listaSegregada = new ArrayList<Segregada>();
    private String iban;
    private String id;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PostConstruct
    public void mostrarDatosCuentaSegregada(){
        this.iban = sesion.getIban();
        this.id = sesion.getCliente();
    }

    public synchronized List<Autorizacion> getAutorizaciones() {
        return gestionGetCuentas.getAutorizaciones(iban, id);
    }

    //Metodo para leer a todos los clientes
    public synchronized List<Segregada> getCuentas(){
        return gestionGetCuentas.getSegregada(iban);
    }

    public synchronized List<Referencia> getReferencia() {return gestionGetCuentas.getReferenciaSegregada(iban);}

}
