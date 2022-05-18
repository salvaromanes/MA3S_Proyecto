package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AccesoAplicacion implements GestionAccesoAplicacion {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void accederAplicacion(String usuario, String contrasena) throws AccesoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioIncorrectoException("accederAplicacion: usuario " + usuario + " incorrecto");
        }

        if(!contrasena.equals(user.getContrasena())){
            throw new ContraseñaIncorrectaException("accederAplicacion: contraseña del usuario " + usuario + " es incorrecta");
        }
    }

    @Override
    public Usuario entrarAplicacion(String usuario, String contrasena) throws AccesoException {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u " + "WHERE u.user LIKE :username", Usuario.class);
        query.setParameter("username", usuario);
        Usuario u = query.getSingleResult();

        return u;
    }
}
