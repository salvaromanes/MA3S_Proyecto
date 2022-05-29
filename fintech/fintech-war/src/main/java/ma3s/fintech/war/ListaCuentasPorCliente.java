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

    public Segregada getSegregada() {
        return segregada;
    }

    public void setSegregada(Segregada segregada) {
        this.segregada = segregada;
    }

    public void setPooled(Pooled pooled) {
        this.pooled = pooled;
    }


    public String verCuenta(Segregada segregada1){
        sesion.setIbanViewTrans(segregada1.getIban());
        sesion.setIban(segregada1.getIban());
        sesion.setCliente(segregada1.getCliente().getIdentificacion());
        return "MasDetallesSegregada.xhtml?faces-redirect=true";
    }

    public String verCuenta2(Pooled pooled1){
        sesion.setIbanViewTrans(pooled1.getIban());
        sesion.setIban(pooled1.getIban());
        sesion.setCliente(pooled1.getCliente().getIdentificacion());
        return "MasDetallesPooled.xhtml?faces-redirect=true";
    }

    public String transS(Segregada segregada1){
        sesion.setIban_transaccion(segregada1.getIban());
        return "TransaccionCliente.xhtml?faces-redirect=true";
    }
    public String transP(Pooled pooled1){
        sesion.setIban_transaccion(pooled1.getIban());
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

    public Boolean listaSegregadas(){
        List<Segregada> cuentasSeg = gestionGetCuentas.getSegregadas(sesion.getUsuario());
        if(cuentasSeg.isEmpty()){
            return  false;
        }
        return true;
    }

    public Boolean listaSegregadaLectura(){
        List<Segregada> cuentasSeg = gestionGetCuentas.getSegregadasAutoLec(sesion.getUsuario());
        if(cuentasSeg.isEmpty()){
            return  false;
        }
        return true;
    }

    public Boolean listaSegregadaEscritura(){
        List<Segregada> cuentasSeg = gestionGetCuentas.getSegregadasAutoEsc(sesion.getUsuario());
        if(cuentasSeg.isEmpty()){
            return  false;
        }
        return true;
    }

    public Boolean listaPooled(){
        List<Pooled> cuentasPoo = gestionGetCuentas.getPooleds(sesion.getUsuario());
        if(cuentasPoo.isEmpty()){
            return  false;
        }
        return true;
    }

    public Boolean listaPooledLectura(){
        List<Pooled> pooledList = gestionGetCuentas.getPooledAutLec(sesion.getUsuario());
        if(pooledList.isEmpty()){
            return  false;
        }
        return true;
    }

    public Boolean listaPooledEscritura(){
        List<Pooled> pooledList = gestionGetCuentas.getPooledAutEsc(sesion.getUsuario());
        if(pooledList.isEmpty()){
            return  false;
        }
        return true;
    }

}