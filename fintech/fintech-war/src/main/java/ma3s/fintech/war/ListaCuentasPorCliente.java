package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionGetCuentasUnCliente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "listCuenta")
@RequestScoped
public class ListaCuentasPorCliente {

    public ListaCuentasPorCliente(){
        Pooled pooled;
        pooled = new Pooled();
        pooled.setEstado("Abierta");
        pooled.setIban("ES70 2548 2154 2655");
        pooled.setFechaApertura(new Date());
    }

    private GestionGetCuentasUnCliente getCuentasUnCliente;

    private List<Pooled> listaPooled = new ArrayList<Pooled>();

    private List<Segregada> segregada = new ArrayList<Segregada>();

    private String IBAN;
    private Boolean esPooled = false;

    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Pooled> getListaPooled() {
        return listaPooled;
    }

    public void setListaPooled(List<Pooled> listaPooled) {
        this.listaPooled = listaPooled;
    }

    public List<Segregada> getSegregada() {
        return segregada;
    }

    public void setSegregada(List<Segregada> segregada) {
        this.segregada = segregada;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Boolean getEsPooled() {
        return esPooled;
    }

    public void setEsPooled(Boolean esPooled) {
        this.esPooled = esPooled;
    }


    public GestionGetCuentasUnCliente getGetCuentasUnCliente() {
        return getCuentasUnCliente;
    }

    public void setGetCuentasUnCliente(GestionGetCuentasUnCliente getCuentasUnCliente) {
        this.getCuentasUnCliente = getCuentasUnCliente;
    }




}

