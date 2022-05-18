package ma3s.fintech.ejb;

import ma3s.fintech.Divisa;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import ma3s.fintech.ejb.excepciones.ValorNoValidoException;

public interface GestionAñadirDivisa {
    public void anadirNuevaDivisa(Divisa divisa, String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException;
    public void editarDivisa(Double cambio, Divisa divisa, String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException, ValorNoValidoException;
    public void eliminarDivisa(Divisa divisa, String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException;
}
