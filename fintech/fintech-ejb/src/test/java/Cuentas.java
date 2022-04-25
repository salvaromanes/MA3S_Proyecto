import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.excepciones.*;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

import static org.junit.Assert.fail;

public class Cuentas {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

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
    public void testAperturaCuentaPooled(){
        final String ibanCuenta = "123";
        final String swiftCuenta = "123";
        final String usuario = "Salva";

        Cuenta cuenta = new Cuenta();
        cuenta.setIban(ibanCuenta);
        cuenta.setSwift(swiftCuenta);

        try{
            gestionAperturaCuenta.abrirCuentaPooled(cuenta.getIban(), cuenta.getSwift(), usuario);
            fail("Debe lanzar una excepcion");
        } catch (CuentaExistenteException e) {
            //ok
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion CuentaExistente");
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion CuentaExistente");
        }
    }

    @Requisitos({"RF5"})
    @Test
    public void testAperturaCuentaSegregada(){
        final String ibanCuenta = "123";
        final String swiftCuenta = "123";
        final String usuario = "Salva";

        Cuenta cuenta = new Cuenta();
        cuenta.setIban(ibanCuenta);
        cuenta.setSwift(swiftCuenta);

        try{
            gestionAperturaCuenta.abrirCuentaSegregate(cuenta.getIban(), cuenta.getSwift(), usuario);
            fail("Debe lanzar una excepcion");
        } catch (CuentaExistenteException e) {
            //ok
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion CuentaExistente");
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion CuentaExistente");
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaNoExistente(){
        final String ibanCuenta = "321";

        Cuenta cuenta = new Cuenta();
        cuenta.setIban(ibanCuenta);

        try{
            gestionCierreCuenta.cerrarCuenta(cuenta.getIban());
            fail("Debe lanzar una excepcion");
        } catch (CuentaNoExistenteException e) {
            //ok
        } catch (CuentaNoVacia cuentaNoVacia) {
            fail("Debe lanzar una excepcion CuentaNoExistente");
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaNoVacia(){
        final String ibanCuenta1 = "111";

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setIban(ibanCuenta1);

        Referencia referencia = new Referencia();
        referencia.setIban(ibanCuenta1);
        referencia.setSaldo(100);

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
