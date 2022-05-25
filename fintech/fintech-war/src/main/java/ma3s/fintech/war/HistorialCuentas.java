package ma3s.fintech.war;


import ma3s.fintech.Cliente;
import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.Transaccion;
import ma3s.fintech.ejb.GestionTransferencia;
import ma3s.fintech.ejb.excepciones.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "historial")
@RequestScoped
public class HistorialCuentas {
    private static final Logger LOGGER = Logger.getLogger(HistorialCuentas.class.getCanonicalName());
    @Inject
    private Sesion sesion;

    @Inject
    private GestionTransferencia gestionTransferencia;


    Segregada segregada;
    Pooled pooled;

    private String iban;

    @PostConstruct
    public void HistorialCuentas(){
        iban = sesion.getIbanViewTrans();
    }

    public Segregada getSegregada() {
        return segregada;
    }

    public void setSegregada(Segregada segregada) {
        this.segregada = segregada;
    }

    public Pooled getPooled() {
        return pooled;
    }

    public void setPooled(Pooled pooled) {
        this.pooled = pooled;
    }


    public synchronized List<Transaccion> getTrans(){
        LOGGER.info("Transacciones");
        List<Transaccion> transaccionList = new ArrayList<>();
        try {
           transaccionList = gestionTransferencia.verTransferencias(iban);
        } catch (CuentaNoExistenteException e) {
            LOGGER.info("CuentaNoExistente");
        }
        return transaccionList;
    }

    /*public synchronized List<Transaccion> getTransPooled(){
        LOGGER.info("Pooled");
        List<Transaccion> transaccionList = gestionTransferencia.verTransferencias2(iban);
        if(transaccionList.isEmpty()){
            transaccionList = new ArrayList<>();
            return transaccionList;
        }
        return transaccionList;
    }*/


    public String fechaSimple(Date date){

        if(date == null){
            return null;
        }
        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }


}