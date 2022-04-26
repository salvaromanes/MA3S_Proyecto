import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.excepciones.*;
import org.junit.Before;
import org.junit.Test;
import javax.naming.NamingException;
import static org.junit.Assert.fail;

public class Administrador {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String APERTURA_CUENTA = "java:global/classes/AperturaCuenta";
    private static final String CIERRE_CUENTA = "java:global/classes/CierreCuenta";
    private static final String AccesoPersonal = "java:global/classes/AccesoPersonal";
    private static final String AnadirAutorizado = "java:global/classes/AnadirAutorizados";


    private GestionAperturaCuenta gestionAperturaCuenta;
    private GestionCierreCuenta gestionCierreCuenta;
    private GestionAccesoPersonal gestionAccesoPersonal;
    private GestionAnadirAutorizados gestionAnadirAutorizados;



    @Before
    public void setup() throws NamingException {
        gestionAperturaCuenta = (GestionAperturaCuenta) SuiteTest.ctx.lookup(APERTURA_CUENTA);
        gestionCierreCuenta = (GestionCierreCuenta) SuiteTest.ctx.lookup(CIERRE_CUENTA);
        gestionAccesoPersonal = (GestionAccesoPersonal) SuiteTest.ctx.lookup(AccesoPersonal);
        gestionAnadirAutorizados = (GestionAnadirAutorizados) SuiteTest.ctx.lookup(AnadirAutorizado);

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

    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmNoEncontrado(){
        final String NOMB = "alv";
        final String CONT = "123";
        Usuario user1 = new Usuario();
        user1.setUser(NOMB);
        user1.setContrasena(CONT);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        }catch (UsuarioNoEncontradoException e) {
            //ok
        }
        catch (UsuarioIncorrectoException e){
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }
        catch (ContraseñaIncorrectaException e){
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmNoValido(){
        final String NOMB = "alv";
        final String CONT = "123";
        Usuario user1 = new Usuario();
        user1.setUser(NOMB);
        user1.setContrasena(CONT);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        }catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        }
        catch (UsuarioIncorrectoException e){
            //ok
        }
        catch (ContraseñaIncorrectaException e){
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        }
    }




    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmContNoValida(){
        final String NOMB = "alv";
        final String CONT = "123";
        Usuario user1 = new Usuario();
        user1.setUser(NOMB);
        user1.setContrasena(CONT);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        }catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de ContasenaIncorrecta");
        }
        catch (UsuarioIncorrectoException e){
            fail("Debe lanzar una excepcion de ContasenaIncorrecta");
        }
        catch (ContraseñaIncorrectaException e){
            //ok;
        }
    }




    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmValido(){
        final String NOMB = "Salva";
        final String CONT = "malaga";
        Usuario user1 = new Usuario();
        user1.setUser(NOMB);
        user1.setContrasena(CONT);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            //ok
        }catch (UsuarioNoEncontradoException e) {
            fail("NO Debe lanzar una excepcion ");
        }
        catch (UsuarioIncorrectoException e){
            fail("NO Debe lanzar una excepcion ");
        }
        catch (ContraseñaIncorrectaException e){
            fail("NO Debe lanzar una excepcion ");
        }
    }





    /*


    }
    @Requisitos({"RF6"})
    @Test


    public void testAdmin(){
        final String NOMB = "Salva";
        Usuario user_aux = new Usuario();
        user_aux.setUser(NOMB);

        try{
           gestionAnadirAutorizados.comprobarAdministrador(user_aux.getUser());
        }
        catch (PersonaNoExisteException e){
            fail("Error al encontrar administrador");
        }
        catch (NoEsAdministrativoException e)

    }

 */



}
