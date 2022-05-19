package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public Usuario entrarAplicacion(String usuario, String contrasena) throws AccesoException, ErrorInternoException, CampoVacioException {
//        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u " + "WHERE u.user LIKE :username", Usuario.class);
//        query.setParameter("username", usuario);
//        Usuario u = query.getSingleResult();
//        find con el user y comprobar que no sea null y la contraseña

        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioIncorrectoException("accederAplicacion: usuario " + usuario + " incorrecto");
        }

        /*
        byte[] contEnc = null;

        try {
            contEnc = MessageDigest.getInstance("SHA").digest(contrasena.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new ErrorInternoException("ERROR. Algoritmo");
        } catch (UnsupportedEncodingException e) {
            throw new ErrorInternoException("ERROR. Codificacion");
        }

        if(contEnc == null){
            throw new CampoVacioException("Contraseña vacia");
        }else if(contEnc != user.getContrasena().getBytes()){
            throw new ContraseñaIncorrectaException("Usuario o contraseña incorrecta");
        }
        */

        if(contrasena == null){
            throw new CampoVacioException("Contraseña vacia");
        }else if(!contrasena.equals(user.getContrasena())){
            throw new ContraseñaIncorrectaException("Usuario o contraseña incorrecta");
        }

        return user;
    }
}
