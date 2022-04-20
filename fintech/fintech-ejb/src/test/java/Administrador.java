import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.excepciones.*;
import org.junit.Before;
import org.junit.Test;
import javax.naming.NamingException;
import static org.junit.Assert.fail;

public class Administrador {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechTest";

    private static final String APERTURA_CUENTA = "java:global/classes/AperturaCuenta";
    private static final String CIERRE_CUENTA = "java:global/classes/CierreCuenta";

    private GestionAperturaCuenta gestionAperturaCuenta;
    private GestionCierreCuenta gestionCierreCuenta;

    @Before
    public void setup() throws NamingException {
        gestionAperturaCuenta = (GestionAperturaCuenta) SuiteTest.ctx.lookup(APERTURA_CUENTA);
        gestionCierreCuenta = (GestionCierreCuenta) SuiteTest.ctx.lookup(CIERRE_CUENTA);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Requisitos({"RF5"})
    @Test
    public void testAperturaCuentaAdminNoEncontrado(){
        final String nombre = "Salvador";

        Usuario usuario = new Usuario();
        usuario.setUser(nombre);

        try{
            gestionAperturaCuenta.comprobarAdministrador(usuario.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }
    }

    @Requisitos({"RF5"})
    @Test
    public void testAperturaCuentaAdminErroneo(){
        final String nombre1 = "MA3S";

        Usuario usuario1 = new Usuario();
        usuario1.setUser(nombre1);

        try{
            gestionAperturaCuenta.comprobarAdministrador(usuario1.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        } catch (UsuarioIncorrectoException e) {
            //ok
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaAdminNoEncontrado(){
        final String nombre = "Salvador";

        Usuario usuario = new Usuario();
        usuario.setUser(nombre);

        try{
            gestionCierreCuenta.comprobarAdministrador(usuario.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaCuentaAdminErroneo(){
        final String nombre1 = "MA3S";

        Usuario usuario1 = new Usuario();
        usuario1.setUser(nombre1);

        try{
            gestionCierreCuenta.comprobarAdministrador(usuario1.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        } catch (UsuarioIncorrectoException e) {
            //ok
        }
    }
}