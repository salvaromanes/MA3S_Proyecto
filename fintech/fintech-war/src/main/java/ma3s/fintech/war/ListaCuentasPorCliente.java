package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.GestionGetCuentasUnCliente;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "listCuenta")
@RequestScoped
public class ListaCuentasPorCliente {

    @Inject
    GestionGetCuentas gestionGetCuentas;

    @Inject
    private Sesion sesion;


    Cliente cliente;
    String iban;
    Segregada segregada;
    Pooled pooled;

    public String verCuenta(Segregada segregada1){
        segregada = segregada1;
        return "HistorialTransacciones.xhtml";
    }

    public String verCuenta(Pooled pooled1){
        pooled = pooled1;
        return "HistorialTransacciones.xhtml";
    }
    public synchronized List<Segregada> getSegregadas(){
        List<Segregada> cuentasSeg = gestionGetCuentas.getSegregadas(sesion.getUsuario());
        if(cuentasSeg != null){
            return  cuentasSeg;
        }
        List<Segregada> aux = new ArrayList<>();
        return aux;
    }

    public synchronized List<Pooled> getPooleds(){
        List<Pooled> cuentasPoo = gestionGetCuentas.getPooleds(sesion.getUsuario());
        if(cuentasPoo != null){
            return  cuentasPoo;
        }
        return null;
    }

}