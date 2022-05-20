package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionGetClientes;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.Cuenta;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "listCuenta")
@RequestScoped
public class ListaCuentasPorCliente {

    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @Inject
    private Cuenta cuenta;


    public synchronized List<Cuenta> getCuentasC(){
        return gestionGetCuentas.getCuentas();
    }
}

