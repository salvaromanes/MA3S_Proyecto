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
            gestionEliminarAutorizados.darBaja(user.getUser(), pA.getId(), empresa);
            //ok
        } catch (PersonaNoExisteException e) {
            fail("No Debe lanzar una excepcion " + e.getMessage());
        } catch (NoEsPAutorizadaException e) {
            fail("No Debe lanzar una excepcion " + e.getMessage());
        } catch (EmpresaNoExistenteException e) {
            fail("No Debe lanzar una excepcion " + e.getMessage());
        }
    }

    @Requisitos({"RF8"})
    @Test

    public void testDarBajaErrorPersonaNoExiste(){
        Usuario user = new Usuario();
        user.setUser("Almudena");
        user.setContrasena("Almu");

        PAutorizada pA = new PAutorizada();
        pA.setNombre("Salva");
        pA.setIdentificacion("12345678S");
        pA.setId(parseLong("1"));

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");
        empresa.setId(parseLong("1"));

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), pA.getId(), empresa);
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e) {
            //ok
        } catch (NoEsPAutorizadaException e) {
            fail("Debe lanzar una excepcion PersonaNoExisteException"  + e.getMessage());
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion PersonaNoExisteException" + e.getMessage());
        }
    }

    @Requisitos({"RF8"})
    @Test

    public void testDarBajaErrorNoEsPAutorizada(){
        Usuario user = new Usuario();
        user.setUser("Almu");
        user.setContrasena("1234");

        Long id = parseLong("222");

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");
        empresa.setId(parseLong("1"));

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), id, empresa);
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion NoEsPAutorizadaException" + e.getMessage());
        } catch (NoEsPAutorizadaException e) {
            //ok
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion NoEsPAutorizadaException" + e.getMessage());
        }
    }
}
