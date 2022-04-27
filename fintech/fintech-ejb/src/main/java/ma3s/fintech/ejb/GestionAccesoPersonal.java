package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import ma3s.fintech.ejb.excepciones.ContraseñaIncorrectaException;




public interface GestionAccesoPersonal {

    public void obtenerPersonal(String usuario, String password)
            throws UsuarioNoEncontradoException, UsuarioIncorrectoException, ContraseñaIncorrectaException;

}
