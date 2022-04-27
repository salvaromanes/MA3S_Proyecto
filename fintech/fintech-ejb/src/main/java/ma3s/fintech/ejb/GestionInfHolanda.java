package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;
import ma3s.fintech.ejb.excepciones.CuentaNoExistenteException;
import ma3s.fintech.ejb.excepciones.ClienteNoExisteException;
import ma3s.fintech.ejb.excepciones.NoEsPAutorizadaException;


public interface GestionInfHolanda {

    public String CuentasApi(Segregada cuenta)
            throws CuentaNoExistenteException;

    public String ClienteApi(Cliente cliente)  throws  ClienteNoExisteException;


    public String  PAutorApi(PAutorizada autorizada) throws NoEsPAutorizadaException;




}
