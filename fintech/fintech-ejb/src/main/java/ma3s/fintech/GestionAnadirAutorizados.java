package ma3s.fintech;


import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.excepciones.PersonaNoExisteException;
import ma3s.fintech.excepciones.EmpresaNoExistenteException;

public interface GestionAnadirAutorizados {

    public void comprobarAdministrador(String usuario)throws  NoEsAdministrativoException, PersonaNoExisteException;

    public void anadirPAut(PAutorizada autorizada, Empresa empresa)  throws  NoEsPAutorizadaException, EmpresaNoExistenteException;



}
