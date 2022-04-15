package ma3s.fintech;

import java.util.List;

import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;
import ma3s.fintech.excepciones.ContraseñaIncorrectaException;




public interface GestionAccesoPersonal {

    public void obtenerPersonal(String usuario, String password)
            throws UsuarioNoEncontradoException, UsuarioIncorrectoException, ContraseñaIncorrectaException;

}
