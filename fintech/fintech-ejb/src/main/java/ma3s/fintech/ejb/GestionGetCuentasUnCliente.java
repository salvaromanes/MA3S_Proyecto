package ma3s.fintech.ejb;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Fintech;

import java.util.List;

public interface GestionGetCuentasUnCliente {
    public List<Fintech> getCuentas(String ident);
}
