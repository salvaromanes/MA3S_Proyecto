package ma3s.fintech;

import java.util.List;
import ma3s.fintech.excepciones.AccesoException;
import ma3s.fintech.excepciones.UsuarioExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;



public interface GestionAccesoPersonal {

    public List<Cliente> obtenerPersonal(String usuario, String contra)
            throws UsuarioNoEncontradoException, UsuarioIncorrectoException;

}
