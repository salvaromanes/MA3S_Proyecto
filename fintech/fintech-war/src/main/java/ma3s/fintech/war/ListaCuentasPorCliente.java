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



    public synchronized List<Segregada> getSegregadas(){
        List<Segregada> cuentasSeg = gestionGetCuentas.getSegregadas(sesion.getUsuario());
        if(cuentasSeg != null){
            return  cuentasSeg;
        }
        return null;
    }

    public synchronized List<Pooled> getPooleds(){
        List<Pooled> cuentasPoo = gestionGetCuentas.getPooleds(sesion.getUsuario());
        if(cuentasPoo != null){
            return  cuentasPoo;
        }
        return null;
    }

}