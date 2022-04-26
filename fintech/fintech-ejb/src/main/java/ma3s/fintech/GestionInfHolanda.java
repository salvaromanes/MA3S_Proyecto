package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaNoExistenteException;
import ma3s.fintech.excepciones.ClienteNoExisteException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;


import java.util.List;


public interface GestionInfHolanda {

    public String CuentasApi(Segregada cuenta)
            throws CuentaNoExistenteException;

    public String ClienteApi(Cliente cliente)  throws  ClienteNoExisteException;


    public String  PAutorApi(PAutorizada autorizada) throws NoEsPAutorizadaException;




}
