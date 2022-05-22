package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionEliminarAutorizados;
import ma3s.fintech.ejb.GestionGetCuentas;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "mostrarDatosCuentaPooled")
@RequestScoped
public class MostrarDatosCuenta {
    private static final Logger LOGGER = Logger.getLogger(MostrarDatosCuenta.class.getCanonicalName());

    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @Inject
    private GestionEliminarAutorizados gestionEliminarAutorizados;

    @Inject
    private Sesion sesion;

    private List<Autorizacion> autorizaciones = new ArrayList<Autorizacion>();
    private List<Pooled> listaPooled = new ArrayList<Pooled>();
    private String iban = "ES8400817251647192321264";
    private String id = "63937528N";

    public synchronized List<Autorizacion> getAutorizaciones() {
        return gestionGetCuentas.getAutorizaciones(iban, id);
    }

    //Metodo para leer a todos los clientes
    public synchronized List<Pooled> getCuentas(){
        return gestionGetCuentas.getPooled(iban);
    }

    public synchronized List<DepositadaEn> getDepositos() {return gestionGetCuentas.getReferenciaPooled(iban); }

}
