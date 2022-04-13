package ma3s.fintech;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import ma3s.fintech.excepciones.AccesoException;
import ma3s.fintech.excepciones.UsuarioExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class AccesoPersonal implements GestionAccesoPersonal {

    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public List<Cliente> obtenerPersonal(String usuario, String contra)
        throws  UsuarioNoEncontradoException, UsuarioIncorrectoException{

        Cliente cliente = em.find(Cliente.class,usuario);

        if(cliente == null){
            throw new UsuarioNoEncontradoException();
        }
       // return new ArrayList<>((Collection<? extends Cliente>) cliente.getUser());
        if(!cliente.getId().equals(usuario)){
            throw new UsuarioIncorrectoException();
        }
        return null;

    }
}
