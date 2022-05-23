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

    @Inject
    private HistorialCuentas historialCuentas;

    Cliente cliente;
    String iban;
    Segregada segregada;
    Pooled pooled;

    public String verCuenta(Segregada segregada1){
        segregada = segregada1;
        return "HistorialTransacciones.xhtml?faces-redirect=true";
    }

    public String verCuenta(Pooled pooled1){
        sesion.setPooled(pooled1);
        return "HistorialTransacciones.xhtml?faces-redirect=true";
    }

    public String transS(Segregada segregada1){
        segregada = segregada1;
        return "TransaccionCliente.xhtml?faces-redirect=true";
    }
    public String transP(Pooled pooled1){
        pooled = pooled1;
        return "TransaccionCliente.xhtml?faces-redirect=true";
    }
    public Pooled getPooled() {
        return pooled;
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


    public synchronized List<Segregada> getSegregadasAutEsc(){
        List<Segregada> cuentasSeg = gestionGetCuentas.getSegregadasAutoEsc(sesion.getUsuario());
        if(cuentasSeg != null){
            return  cuentasSeg;
        }
        List<Segregada> aux = new ArrayList<>();
        return aux;
    }

    public synchronized List<Segregada> getSegregadasAutLec(){
        List<Segregada> cuentasSeg = gestionGetCuentas.getSegregadasAutoLec(sesion.getUsuario());
        if(cuentasSeg != null){
            return  cuentasSeg;
        }
        List<Segregada> aux = new ArrayList<>();
        return aux;
    }


    public synchronized List<Pooled> getPooledAutLec(){
        List<Pooled> pooledList = gestionGetCuentas.getPooledAutLec(sesion.getUsuario());
        if(pooledList != null){
            return  pooledList;
        }
        List<Pooled> aux = new ArrayList<>();
        return aux;
    }

    public synchronized List<Pooled> getPooledAutEsc(){
        List<Pooled> pooledList = gestionGetCuentas.getPooledAutEsc(sesion.getUsuario());
        if(pooledList != null){
            return  pooledList;
        }
        List<Pooled> aux = new ArrayList<>();
        return aux;
    }

}