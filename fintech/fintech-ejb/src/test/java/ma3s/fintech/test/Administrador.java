package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;
import org.junit.Before;
import org.junit.Test;
import javax.naming.NamingException;
import static org.junit.Assert.fail;

public class Administrador {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";
    private static final String ACCESOPERSONAL = "java:global/classes/AccesoPersonal";

    private ma3s.fintech.ejb.GestionAccesoPersonal gestionAccesoPersonal;

    @Before
    public void setup() throws NamingException {
        gestionAccesoPersonal = (ma3s.fintech.ejb.GestionAccesoPersonal) SuiteTest.ctx.lookup(ACCESOPERSONAL);

        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmNoEncontrado() {
        final String nomb = "alv";
        final String cont = "123";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        } catch (ContraseñaIncorrectaException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmNoValido() {
        final String nomb = "MA3S";
        final String cont = "123";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        } catch (UsuarioIncorrectoException e) {
            //ok
        } catch (ContraseñaIncorrectaException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        }
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmContNoValida() {
        final String nomb = "Salva";
        final String cont = "123";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de ContasenaIncorrecta");
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de ContasenaIncorrecta");
        } catch (ContraseñaIncorrectaException e) {
            //ok;
        }
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmValido() {
        final String nomb = "Salva";
        final String cont = "malaga";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            //ok
        } catch (UsuarioNoEncontradoException e) {
            fail("NO Debe lanzar una excepcion ");
        } catch (UsuarioIncorrectoException e) {
            fail("NO Debe lanzar una excepcion ");
        } catch (ContraseñaIncorrectaException e) {
            fail("NO Debe lanzar una excepcion ");
        }
    }
}
