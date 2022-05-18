package ma3s.fintech.ejb;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.ContraseñaIncorrectaException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CrearDepositadaEn implements GestionAccesoPersonal {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void obtenerPersonal(String usuario, String password)
        throws  UsuarioNoEncontradoException, UsuarioIncorrectoException, ContraseñaIncorrectaException {

        Usuario user1 = em.find(Usuario.class,usuario);

        if(user1 == null){
            throw new UsuarioNoEncontradoException("El usuario : " + usuario + " no ha sido encontrado");
        }
        if(user1.getEsAdmin() == false){
            throw new UsuarioIncorrectoException("El usuario: " + usuario + " no es administrador");
        }

        if(user1.getContrasena().isEmpty() || user1.getContrasena() != password || password == null){
            throw new ContraseñaIncorrectaException("La contraseña no es correcta");
        }

    }
}
