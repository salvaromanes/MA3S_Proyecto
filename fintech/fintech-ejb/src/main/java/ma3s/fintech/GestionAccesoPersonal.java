package ma3s.fintech;

import java.util.List;

import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;



public interface GestionAccesoPersonal {

    public void obtenerPersonal(String usuario)
            throws UsuarioNoEncontradoException, UsuarioIncorrectoException;

}
