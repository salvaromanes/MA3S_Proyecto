package ma3s.fintech;

import java.util.List;
import ma3s.fintech.excepciones.AccesoException;
import ma3s.fintech.excepciones.UsuarioExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class AccesoPersonal implements GestionAccesoPersonal {

    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public List<Cliente> obtenerPersonal(String usuario, String contra)
        throws  UsuarioIncorrectoException{

        Cliente cliente = em.find(Cliente.class,usuario);

        if(usuario == null){
            throw new UsuarioIncorrectoException();
        }
        return null;
    }
}
