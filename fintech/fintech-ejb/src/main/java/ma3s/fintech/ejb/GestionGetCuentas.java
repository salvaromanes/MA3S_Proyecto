package ma3s.fintech.ejb;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Segregada;

import java.util.List;

public interface GestionGetCuentas {
    public List<Cuenta> getCuentas();

    public Segregada getSegregada(String iban);
}


