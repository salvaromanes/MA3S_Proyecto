package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
