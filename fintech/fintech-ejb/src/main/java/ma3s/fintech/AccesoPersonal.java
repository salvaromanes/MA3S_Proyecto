package ma3s.fintech;

import java.util.List;

import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class AccesoPersonal implements GestionAccesoPersonal {

    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public List<Usuario> obtenerPersonal(String usuario, String contra)
        throws  UsuarioNoEncontradoException, UsuarioIncorrectoException{

        Usuario user1 = em.find(Usuario.class,usuario);

        if(user1 == null){
            throw new UsuarioNoEncontradoException();
        }
        if(user1.getEsAdmin() == false){
            throw new UsuarioIncorrectoException();
        }

        return (List<Usuario>) user1;

    }
}
