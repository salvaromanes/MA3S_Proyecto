package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.GestionTransferencia;
import ma3s.fintech.ejb.excepciones.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named(value = "transcliente")
@RequestScoped
public class TransaccionClientes {

    @Inject
    private Sesion sesion;
    @Inject
    private GestionGetCuentas gestionGetCuentas;
    @Inject
    private GestionTransferencia gestionTransferencia;

    Pooled pooled;
    Segregada segregada;
    private Transaccion transaccion;

    private String ibanOrigen;
    private String ibanDestino;
    private Double Cantidad;
    private String tipo;
    private String divisaOrigen;
    private String divisaDestino;

    @PostConstruct
    public void Contructor(){
        pooled = gestionGetCuentas.getUPooled(sesion.getIban_transaccion());
        segregada = gestionGetCuentas.getUSegregada(sesion.getIban_transaccion());

        if(pooled != null)
            ibanOrigen = pooled.getIban();
        if(segregada != null)
            ibanOrigen = segregada.getIban();
    }

    public Pooled getPooled() {
        return pooled;
    }

    public Segregada getSegregada() {
        return segregada;
    }

    public void setPooled(Pooled pooled) {
        this.pooled = pooled;
    }

    public void setSegregada(Segregada segregada) {
        this.segregada = segregada;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }


    public String realizarTransaccionCliente() {
        Transaccion transaccion= new Transaccion();
        if(pooled != null){
            try {
                Cuenta cuenta = gestionGetCuentas.getCuenta(pooled.getIban());
                transaccion.setCuentaOrigen(cuenta);
            } catch (ClienteNoExisteException e) {
                e.printStackTrace();
            }
        }else if(segregada != null){
            try {
                Cuenta cuenta = gestionGetCuentas.getCuenta(segregada.getIban());
                transaccion.setCuentaOrigen(cuenta);
            } catch (ClienteNoExisteException e) {
                e.printStackTrace();
            }
        }
        try{
            Divisa divisa = gestionGetCuentas.getDivisa(divisaOrigen);
            transaccion.setDivisaEmisor(divisa);
        } catch (DatosIncorrectosException e) {
            e.printStackTrace();
        }
        try{
            Divisa divisa = gestionGetCuentas.getDivisa(divisaDestino);
            transaccion.setDivisaReceptor(divisa);
        } catch (DatosIncorrectosException e) {
            e.printStackTrace();
        }
        try{
            Cuenta cuenta = gestionGetCuentas.getCuenta(ibanDestino);
            transaccion.setCuentaDestino(cuenta);
        } catch (ClienteNoExisteException e) {
            e.printStackTrace();
        }
        transaccion.setCantidad(Cantidad);
        transaccion.setTipo(tipo);
        transaccion.setFechaInstruccion(new Date());

        try{
            gestionTransferencia.crearTransaccion(transaccion);
            return "cuentasporUsuario.xhtml?faces-redirect=true";
        } catch (CampoVacioException e) {
            e.printStackTrace();
        }

        return null;
    }


    public String getIbanOrigen() {
        return ibanOrigen;
    }

    public void setIbanOrigen(String ibanOrigen) {
        this.ibanOrigen = ibanOrigen;
    }

    public String getIbanDestino() {
        return ibanDestino;
    }

    public void setIbanDestino(String ibanDestino) {
        this.ibanDestino = ibanDestino;
    }

    public Double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Double cantidad) {
        Cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDivisaOrigen() {
        return divisaOrigen;
    }

    public void setDivisaOrigen(String divisaOrigen) {
        this.divisaOrigen = divisaOrigen;
    }

    public String getDivisaDestino() {
        return divisaDestino;
    }

    public void setDivisaDestino(String divisaDestino) {
        this.divisaDestino = divisaDestino;
    }
}