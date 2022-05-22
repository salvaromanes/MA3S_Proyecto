package ma3s.fintech.war;


import ma3s.fintech.Cliente;
import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.Transaccion;
import ma3s.fintech.ejb.GestionTransferencia;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ErrorOrigenTransaccionException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.SaldoNoSuficiente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "hisotrial")
@RequestScoped
public class HistorialCuentas {

    @Inject
    private GestionTransferencia gestionTransferencia;


    Segregada segregada;
    Pooled pooled;

    public void setSegregada(Segregada segregada) {
        this.segregada = segregada;
    }

    public String verCuenta(Segregada segregada1){
        segregada = segregada1;
        return "HistorialTransacciones.xhtml";
    }

    public String verCuenta(Pooled pooled1){
        pooled = pooled1;
        return "HistorialTransacciones.xhtml";
    }
   public synchronized List<Transaccion> getTransSeg(){
       List<Transaccion> transaccionList = gestionTransferencia.verTransferencias(segregada);
       if(transaccionList != null){
           return transaccionList;
       }
       List<Transaccion> aux = new ArrayList<>();
       return aux;
   }

    public synchronized List<Transaccion> getTransPooled(){
        List<Transaccion> transaccionList = gestionTransferencia.verTransferencias2(pooled);
        if(transaccionList != null){
            return transaccionList;
        }
        List<Transaccion> aux = new ArrayList<>();
        return aux;
    }



}