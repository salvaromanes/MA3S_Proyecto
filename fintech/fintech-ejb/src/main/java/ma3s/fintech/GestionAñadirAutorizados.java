package ma3s.fintech;


import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.excepciones.PersonaNoExisteException;

import java.util.Date;

public interface GestionAñadirAutorizados {

    public void comprobarAdministrador(String usuario)throws  NoEsPAutorizadaException, PersonaNoExisteException;

}
