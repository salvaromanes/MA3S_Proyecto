import ma3s.fintech.Cuenta;
import ma3s.fintech.GestionAperturaCuenta;
import ma3s.fintech.Usuario;
import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;
import org.junit.Before;
import org.junit.Test;
import javax.naming.NamingException;
import static org.junit.Assert.fail;

public class Fintech {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechTest";

    private static final String CUENTA = "java:global/classes/Cuenta";

    private GestionAperturaCuenta gestionAperturaCuenta;

    @Before
    public void setup() throws NamingException {
        gestionAperturaCuenta = (GestionAperturaCuenta) SuiteTest.ctx.lookup(CUENTA);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    //@Requisito({"RF5"})
    @Test
    public void testAperturaCuenta(){
        final String nombre = "Salvador";
        final String ibanCuenta = "123";
        final String swiftCuenta = "123";

        Usuario usuario = new Usuario();
        usuario.setUser(nombre);

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
}
