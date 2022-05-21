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


    Cliente cliente;
    String iban;


    public synchronized List<Fintech> getFintech(){
        List<Fintech> fintechList = cliente.getCuentasFintech();
        return fintechList;
    }

    public synchronized Segregada getSegregada(String iban){
        return gestionGetCuentas.getSegregada(iban);
    }

}