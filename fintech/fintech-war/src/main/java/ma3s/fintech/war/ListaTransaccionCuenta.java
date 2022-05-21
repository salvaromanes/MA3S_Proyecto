package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.GestionListaCuenta;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named(value = "listCuenta")
@RequestScoped
public class ListaTransaccionCuenta {

    @Inject
    GestionListaCuenta gestionListaCuenta;

    public synchronized Transaccion getTransaccion(String iban){

        return gestionListaCuenta.getTransaccion(iban);
    }



}