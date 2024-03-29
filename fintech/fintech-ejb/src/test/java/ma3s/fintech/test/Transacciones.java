package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.Cuenta;
import ma3s.fintech.Transaccion;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.*;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import static org.junit.Assert.fail;
import java.util.Date;

import static java.lang.Long.parseLong;

public class Transacciones {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";
    private static final String TRANSFERENCIAS = "java:global/classes/Transferencias";

    private ma3s.fintech.ejb.GestionTransferencia gestionTransferencia;

    @Before
    public void setup() throws NamingException {
        gestionTransferencia = (ma3s.fintech.ejb.GestionTransferencia) SuiteTest.ctx.lookup(TRANSFERENCIAS);

        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    /*
        Se pretende hacer una transferencia por un usuario que no existe en la base de datos
     */
    @Requisitos({"RF14"})
    @Test
    public void TestTransferenciaClientePorUsuarioNoExistente(){
        Transaccion trans = new Transaccion();
        trans.setIdUnico("1");
        trans.setCantidad(Double.parseDouble("20"));
        trans.setFechaInstruccion(new Date());
        trans.setTipo("NULO");

        try{
            gestionTransferencia.transferenciaCliente(trans, parseLong("99009"));
            fail("No se han capturado errores y se esperaba PersonaExisteException");
        } catch (SaldoNoSuficiente e) {
            fail("Se ha capturado saldoNoSuficiente y se esperaba PersonaNoExisteException... " + e.getMessage());
        } catch (PersonaNoExisteException e) {
            // ok
        } catch (CampoVacioException e) {
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException... " + e.getMessage());
        } catch (ErrorOrigenTransaccionException e) {
            fail("Se ha capturado ErrorOrigenTransaccionException y se esperaba PersonaNoExisteException ... " + e.getMessage());
        }
    }

    /*
        Se pretende hacer una transferencia con algunos campos obligatorios vacios
     */
    @Requisitos({"RF14"})
    @Test
    public void TestTransferenciaClienteConCamposVacio(){
        Transaccion trans = new Transaccion();
        trans.setIdUnico("1");
        trans.setCantidad(Double.parseDouble("20"));
        trans.setFechaInstruccion(null);
        trans.setTipo("NULO");

        try{
            gestionTransferencia.transferenciaCliente(trans, 223L);
            fail("No se han capturado errores y se esperaba CampoVacioException");
        } catch (SaldoNoSuficiente e) {
            fail("Se ha capturado saldoNoSuficiente y se esperaba CampoVacioException... " + e.getMessage());
        } catch (PersonaNoExisteException e) {
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException... " + e.getMessage());
        } catch (CampoVacioException e) {
            //ok
        } catch (ErrorOrigenTransaccionException e) {
            fail("Se ha capturado ErrorOrigenTransaccionException y se esperaba CampoVacioException... " + e.getMessage());
        }
    }

    /*
        Se pretende hacer una transferencia con una cuenta que o bien no tiene acceso ese usuario
        o bien no existe
     */
    @Requisitos({"RF14"})
    @Test
    public void TestTransferenciaPAutorizadaErrorCuentaOrigen(){
        Cuenta cuenta = new Cuenta();
        cuenta.setIban("131516");
        cuenta.setSwift("1111");

        Transaccion trans = new Transaccion();
        trans.setIdUnico("1");
        trans.setCantidad(Double.parseDouble("20"));
        trans.setFechaInstruccion(new Date());
        trans.setCuentaOrigen(cuenta);
        trans.setTipo("NULO");

        try{
            gestionTransferencia.transferenciaAutorizado (parseLong("1"), parseLong("1"), trans);
            fail("No se han capturado errores y se esperaba ErrorOrigenTransaccionException");
        } catch (SaldoNoSuficiente e) {
            fail("Se ha capturado saldoNoSuficiente y se esperaba ErrorOrigenTransaccionException... " + e.getMessage());
        } catch (PersonaNoExisteException e) {
            fail("Se ha capturado PersonaNoExisteException y se esperaba ErrorOrigenTransaccionException... " + e.getMessage());
        } catch (CampoVacioException e) {
            fail("Se ha capturado CampoVacioException y se esperaba ErrorOrigenTransaccionException... " + e.getMessage());
        } catch (ErrorOrigenTransaccionException e) {
            // ok
        } catch (EmpresaNoExistenteException e) {
            fail("Se ha capturado EmpresaNoExistenteException y se esperaba ErrorOrigenTransaccionException... " + e.getMessage());
        }
    }

}
