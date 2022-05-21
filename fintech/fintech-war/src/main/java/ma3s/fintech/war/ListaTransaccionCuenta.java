package ma3s.fintech.war;


import ma3s.fintech.Cliente;
import ma3s.fintech.Cuenta;
import ma3s.fintech.Fintech;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.GestionGetCuentas;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "listCuenta")
@RequestScoped
public class ListaTransaccionCuenta {

    @Inject
    GestionGetCuentas gestionGetCuentas;

    Cuenta cuenta;


    public synchronized List<Fintech> getFintech(){
        List<Fintech> fintechList = cliente.getCuentasFintech();
        return fintechList;
    }

    public synchronized Segregada getSegregada(String iban){
        return gestionGetCuentas.getSegregada(iban);
    }



}