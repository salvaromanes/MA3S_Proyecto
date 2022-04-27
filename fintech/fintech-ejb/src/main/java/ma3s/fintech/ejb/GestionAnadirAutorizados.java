package ma3s.fintech.ejb;


import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.NoEsAdministrativoException;
import ma3s.fintech.ejb.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.EmpresaNoRelacException;

public interface GestionAnadirAutorizados {

    void comprobarAdministrador(String usuario)throws  NoEsAdministrativoException, PersonaNoExisteException;




    void anadirPAut(PAutorizada autorizada, Empresa empresa, String usuario) throws NoEsPAutorizadaException, EmpresaNoExistenteException, PersonaNoExisteException, EmpresaNoRelacException, NoEsAdministrativoException;
}
