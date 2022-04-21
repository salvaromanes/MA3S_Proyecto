package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaNoExistenteException;

import java.util.List;


public interface GestionInfHolanda {

    public List<Cuenta> CuentasAbiertas(Cuenta cuenta)
            throws CuentaNoExistenteException;

}
