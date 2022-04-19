import ma3s.fintech.*;
import ma3s.fintech.excepciones.*;
import org.junit.Before;
import org.junit.Test;
import javax.naming.NamingException;
import static org.junit.Assert.fail;

public class Fintech {
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

    //@Requisito({"RF5"})
    @Test
    public void testAperturaCuenta(){
        final String nombre = "Salvador";
        final String nombre1 = "MA3S";
        final String ibanCuenta = "123";
        final String swiftCuenta = "123";

        Usuario usuario = new Usuario();
        usuario.setUser(nombre);

        Usuario usuario1 = new Usuario();
        usuario1.setUser(nombre1);

        Cuenta cuenta = new Cuenta();
        cuenta.setIban(ibanCuenta);
        cuenta.setSwift(swiftCuenta);

        try{
            gestionAperturaCuenta.comprobarAdministrador(usuario.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }

        try{
            gestionAperturaCuenta.comprobarAdministrador(usuario1.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        } catch (UsuarioIncorrectoException e) {
            //ok
        }

        try{
            gestionAperturaCuenta.abrirCuentaPooled(cuenta.getIban(), cuenta.getSwift());
            fail("Debe lanzar una excepcion");
        } catch (CuentaExistenteException e) {
            //ok
        }

        try{
            gestionAperturaCuenta.abrirCuentaSegregate(cuenta.getIban(), cuenta.getSwift());
            fail("Debe lanzar una excepcion");
        } catch (CuentaExistenteException e) {
            //ok
        }
    }

    //@Requisito({"RF9"})
    @Test
    public void testCierreCuenta(){
        final String nombre = "Salvador";
        final String nombre1 = "MA3S";
        final String ibanCuenta = "321";
        final String ibanCuenta1 = "111";

        Usuario usuario = new Usuario();
        usuario.setUser(nombre);

        Usuario usuario1 = new Usuario();
        usuario1.setUser(nombre1);

        Cuenta cuenta = new Cuenta();
        cuenta.setIban(ibanCuenta);

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setIban(ibanCuenta1);

        Referencia referencia = new Referencia();
        referencia.setIban(ibanCuenta1);
        referencia.setSaldo(100);

        try{
            gestionCierreCuenta.comprobarAdministrador(usuario.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }

        try{
            gestionCierreCuenta.comprobarAdministrador(usuario1.getUser());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        } catch (UsuarioIncorrectoException e) {
            //ok
        }

        try{
            gestionCierreCuenta.cerrarCuenta(cuenta.getIban());
            fail("Debe lanzar una excepcion");
        } catch (CuentaNoExistenteException e) {
            //ok
        } catch (CuentaNoVacia cuentaNoVacia) {
            fail("Debe lanzar una excepcion CuentaNoExistente");
        }

        try{
            gestionCierreCuenta.cerrarCuenta(cuenta1.getIban());
            fail("Debe lanzar una excepcion");
        } catch (CuentaNoExistenteException e) {
            fail("Debe lanzar una excepcion CuentaNoVacia");
        } catch (CuentaNoVacia cuentaNoVacia) {
            //ok
        }
    }
}
