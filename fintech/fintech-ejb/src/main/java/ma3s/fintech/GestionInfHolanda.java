package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaNoExistenteException;
import ma3s.fintech.excepciones.ClienteNoExisteException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;


import java.util.List;


public interface GestionInfHolanda {

    public void CuentasApi(Cuenta cuenta)
            throws CuentaNoExistenteException;

    public void ClienteApi(Cliente cliente)  throws  ClienteNoExisteException;


    public void  PAutorApi(PAutorizada autorizada) throws NoEsPAutorizadaException;

}
