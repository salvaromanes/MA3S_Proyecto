package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import org.junit.Before;
import ma3s.fintech.ejb.excepciones.*;
import ma3s.fintech.*;
import org.junit.Test;

import javax.naming.NamingException;

import static java.lang.Long.parseLong;
import static org.junit.Assert.fail;

public class Autorizados {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String ELIMINAR_AUTORIZADOS = "java:global/classes/EliminarAutorizados";

    private ma3s.fintech.ejb.GestionEliminarAutorizados gestionEliminarAutorizados;

    @Before
    public void setup() throws NamingException {
        gestionEliminarAutorizados = (ma3s.fintech.ejb.GestionEliminarAutorizados) SuiteTest.ctx.lookup(ELIMINAR_AUTORIZADOS);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Requisitos({"RF8"})
    @Test

    public void testDarBaja(){
        Usuario user = new Usuario();
        user.setUser("Almu");
        user.setContrasena("Almu");

        PAutorizada pA = new PAutorizada();
        pA.setId(parseLong("256"));
        pA.setNombre("Salva");
        pA.setIdentificacion("12345678S");
        pA.setId(parseLong("1"));

        Empresa empresa = new Empresa();
        empresa.setId(parseLong("124"));
        empresa.setIdentificacion("UMA");

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), pA, empresa);
            //ok
        } catch (PersonaNoExisteException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (NoEsPAutorizadaException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (EmpresaNoExistenteException e) {
            fail("No Debe lanzar una excepcion ");
        }
    }

    @Requisitos({"RF8"})
    @Test

    public void testDarBajaErrorPersonaNoExiste(){
        Usuario user = new Usuario();
        user.setUser("Almudena");

        PAutorizada pA = new PAutorizada();
        pA.setNombre("Salva");
        pA.setIdentificacion("12345678S");
        pA.setId(parseLong("1"));
        pA.setId(parseLong("1"));

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");
        empresa.setId(parseLong("1"));

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), pA, empresa);
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e) {
            //ok
        } catch (NoEsPAutorizadaException e) {
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        }
    }

    @Requisitos({"RF8"})
    @Test

    public void testDarBajaErrorNoEsPAutorizada(){
        Usuario user = new Usuario();
        user.setUser("Almu");

        PAutorizada pA = new PAutorizada();
        pA.setNombre("Salvador");
        pA.setId(1234L);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), pA, empresa);
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        } catch (NoEsPAutorizadaException e) {
            //ok
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        }
    }
}
