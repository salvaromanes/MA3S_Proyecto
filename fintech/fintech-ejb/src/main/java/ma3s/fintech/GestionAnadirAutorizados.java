package ma3s.fintech;


import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.excepciones.PersonaNoExisteException;
import ma3s.fintech.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.excepciones.EmpresaNoRelacException;

public interface GestionAnadirAutorizados {

    void comprobarAdministrador(String usuario)throws  NoEsAdministrativoException, PersonaNoExisteException;




    void anadirPAut(PAutorizada autorizada, Empresa empresa, String usuario) throws NoEsPAutorizadaException, EmpresaNoExistenteException, PersonaNoExisteException, EmpresaNoRelacException, NoEsAdministrativoException;
}
