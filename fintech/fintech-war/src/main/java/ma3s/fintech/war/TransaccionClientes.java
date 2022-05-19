package ma3s.fintech.war;


import ma3s.fintech.Individual;
import ma3s.fintech.Transaccion;
import ma3s.fintech.ejb.GestionTransferencia;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "transcliente")
@RequestScoped
public class TransaccionClientes {

    @Inject
    private GestionTransferencia gestionTransferencia;

    private Transaccion transaccion;

    private String cuenta;
    private String div;
    private double cantidad;


    public double getCantidad() {
        return cantidad;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public String getDiv() {
        return div;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String realizarTransaccionCliente() {
        return null;
    }
}

