package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.GestionCierreCuenta;
import ma3s.fintech.ejb.excepciones.*;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

import static org.junit.Assert.fail;

public class Cuentas {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String APERTURA_CUENTA = "java:global/classes/AperturaCuenta";
    private static final String CIERRE_CUENTA = "java:global/classes/CierreCuenta";

    private ma3s.fintech.ejb.GestionAperturaCuenta gestionAperturaCuenta;
    private ma3s.fintech.ejb.GestionCierreCuenta gestionCierreCuenta;

    @Before
    public void setup() throws NamingException {
        gestionAperturaCuenta = (ma3s.fintech.ejb.GestionAperturaCuenta) SuiteTest.ctx.lookup(APERTURA_CUENTA);
        gestionCierreCuenta = (ma3s.fintech.ejb.GestionCierreCuenta) SuiteTest.ctx.lookup(CIERRE_CUENTA);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Requisitos({"RF5"})
    @Test
    public void testAperturaCuentaPooled(){
        final String ibanCuenta = "12345678";
        final String swiftCuenta = "12384";
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
        final String ibanCuenta = "12315";
        final String swiftCuenta = "12323";
        final String usuario = "Salva";

        try{
            gestionAperturaCuenta.abrirCuentaSegregate(ibanCuenta, swiftCuenta, usuario);
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
    public void testAperturaCuentaPooledOk(){
        final String ibanCuenta = "132";
        final String swiftCuenta = "132";
        final String usuario = "Salva";

        Cuenta cuenta = new Cuenta();
        cuenta.setIban(ibanCuenta);
        cuenta.setSwift(swiftCuenta);

        try{
            gestionAperturaCuenta.abrirCuentaPooled(cuenta.getIban(), cuenta.getSwift(), usuario);
            //ok
        } catch (CuentaExistenteException e) {
            fail("No debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("No debe lanzar una excepcion");
        } catch (UsuarioIncorrectoException e) {
            fail("No debe lanzar una excepcion");
        }
    }

    @Requisitos({"RF5"})
    @Test
    public void testAperturaCuentaSegregadaOk(){
        final String ibanCuenta = "9999";
        final String swiftCuenta = "8888";
        final String usuario = "Salva";

        try{
            gestionAperturaCuenta.abrirCuentaSegregate(ibanCuenta, swiftCuenta, usuario);
            //ok
        } catch (CuentaExistenteException e) {
            fail("No debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("No debe lanzar una excepcion");
        } catch (UsuarioIncorrectoException e) {
            fail("No debe lanzar una excepcion");
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaNoExistente() {
        final String ibanCuenta = "321";
        final String usuario = "Salva";

        Cuenta cuenta = new Cuenta();
        cuenta.setIban(ibanCuenta);

        try {
            gestionCierreCuenta.cerrarCuenta(cuenta.getIban(), usuario);
            fail("Debe lanzar una excepcion");
        } catch (CuentaNoExistenteException e) {
            //ok
        } catch (CuentaNoVacia cuentaNoVacia) {
            fail("Debe lanzar una excepcion CuentaNoExistente");
        } catch (UsuarioNoEncontradoException e){
            fail("Debe lanzar una excepcion CuentaNoExistente");
        } catch (UsuarioIncorrectoException e){
            fail("Debe lanzar una excepcion CuentaNoExistente");
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaNoVacia(){
        final String ibanCuenta1 = "123846";
        final String usuario = "Salva";

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setIban(ibanCuenta1);

        Referencia referencia = new Referencia();
        referencia.setIban(ibanCuenta1);
        referencia.setSaldo(100);

        try{
            gestionCierreCuenta.cerrarCuenta(cuenta1.getIban(), usuario);
            fail("Debe lanzar una excepcion");
        } catch (CuentaNoExistenteException e) {
            fail("Debe lanzar una excepcion CuentaNoVacia");
        } catch (CuentaNoVacia cuentaNoVacia) {
            //ok
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion CuentaNoVacia");
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion CuentaNoVacia");
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreUsuarioNoEncontrado(){
        final String usuario = "SalvaOB";
        final String ibanCuenta1 = "123221";

        try{
            gestionCierreCuenta.cerrarCuenta(ibanCuenta1, usuario);
            fail("Debe lanzar una excepcion");
        } catch (CuentaNoExistenteException e) {
            fail("Debe lanzar una excepcion UsuarioNoEncontrado");
        } catch (CuentaNoVacia cuentaNoVacia) {
            fail("Debe lanzar una excepcion UsuarioNoEncontrado");
        } catch (UsuarioNoEncontradoException e) {
            //Ok
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion UsuarioNoEncontrado");
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaUsuarioIncorrecto(){
        final String ibanCuenta1 = "111";
        final String usuario = "Salva";

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setIban(ibanCuenta1);

        Referencia referencia = new Referencia();
        referencia.setIban(ibanCuenta1);
        referencia.setSaldo(100);

        try{
            gestionCierreCuenta.cerrarCuenta(cuenta1.getIban(), usuario);
            fail("Debe lanzar una excepcion");
        } catch (CuentaNoExistenteException e) {
            fail("Debe lanzar una excepcion UsuarioIncorrecto");
        } catch (CuentaNoVacia cuentaNoVacia) {
            fail("Debe lanzar una excepcion UsuarioIncorrecto");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion UsuarioIncorrecto");
        } catch (UsuarioIncorrectoException e) {
            //Ok
        }
    }

    @Requisitos({"RF9"})
    @Test
    public void testCierreCuentaOk(){
        final String ibanCuenta1 = "123456789012345";
        final String usuario = "Salva";

        try{
            gestionCierreCuenta.cerrarCuenta(ibanCuenta1, usuario);
            //Ok
        } catch (CuentaNoExistenteException e) {
            fail("No debe lanzar una excepcion");
        } catch (CuentaNoVacia cuentaNoVacia) {
            fail("No debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("No debe lanzar una excepcion");
        } catch (UsuarioIncorrectoException e) {
            fail("No debe lanzar una excepcion");
        }
    }
}
