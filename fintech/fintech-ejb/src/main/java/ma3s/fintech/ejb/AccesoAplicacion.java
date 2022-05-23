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

    private String encriptar(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    @Override
    public Usuario entrarAplicacion(String usuario, String contrasena) throws AccesoException, CampoVacioException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioIncorrectoException("accederAplicacion: usuario " + usuario + " incorrecto");
        }

//        if(user.getEsAdmin()){
//            throw new UsuarioIncorrectoException("El usuario es administrador");
//        }

        if(encriptar(contrasena) == null){
            throw new CampoVacioException("Contraseña vacia");
        }else if(!encriptar(contrasena).equals(user.getContrasena())){
            throw new ContraseñaIncorrectaException("Usuario o contraseña incorrecta");
        }

//        if(contrasena == null){
//            throw new CampoVacioException("Contraseña vacia");
//        }else if(!contrasena.equals(user.getContrasena())){
//            throw new ContraseñaIncorrectaException("Usuario o contraseña incorrecta");
//        }

        return user;
    }

    @Override
    public Usuario entrarAplicacionAdministrador(String usuario, String contrasena) throws AccesoException, CampoVacioException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioIncorrectoException("accederAplicacion: usuario " + usuario + " incorrecto");
        }

        if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException("El usuario no es administrador");
        }

        if(encriptar(contrasena) == null){
            throw new CampoVacioException("Contraseña vacia");
        }else if(!encriptar(contrasena).equals(user.getContrasena())){
            throw new ContraseñaIncorrectaException("Usuario o contraseña incorrecta");
        }

//        if(contrasena == null){
//            throw new CampoVacioException("Contraseña vacia");
//        }else if(!contrasena.equals(user.getContrasena())){
//            throw new ContraseñaIncorrectaException("Usuario o contraseña incorrecta");
//        }

        return user;
    }

    @Override
    public Usuario refrescarUsuario(Usuario u) throws CampoVacioException, AccesoException {
        entrarAplicacion(u.getUser(), u.getContrasena());
        Usuario user = em.find(Usuario.class, u.getUser());
        em.refresh(user);
        return user;
    }

//    @Override
//    public Usuario refrescarUsuarioAdmin(Usuario u) throws CampoVacioException, AccesoException {
//        entrarAplicacion(u.getUser(), u.getContrasena());
//        Usuario user = em.find(Usuario.class, u.getUser());
//        em.refresh(user);
//        return user;
//    }
}
