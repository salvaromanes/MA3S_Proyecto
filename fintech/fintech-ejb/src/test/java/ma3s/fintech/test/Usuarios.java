package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.GestionConcesionAutorizacion;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.CuentaNoExistenteException;
import ma3s.fintech.ejb.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

import static java.lang.Long.parseLong;
import static org.junit.Assert.fail;

public class Usuarios {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String CONCESION_AUTORIZACION = "java:global/classes/ConcesionAutorizacion";
    private static final String ACCESOAPLICACION = "java:global/classes/AccesoAplicacion";

    private ma3s.fintech.ejb.GestionConcesionAutorizacion gestionConcesionAutorizacion;
    private ma3s.fintech.ejb.GestionAccesoAplicacion gestionAccesoAplicacion;

    @Before
    public void setup() throws NamingException {
        gestionConcesionAutorizacion = (ma3s.fintech.ejb.GestionConcesionAutorizacion) SuiteTest.ctx.lookup(CONCESION_AUTORIZACION);
        gestionAccesoAplicacion = (ma3s.fintech.ejb.GestionAccesoAplicacion) SuiteTest.ctx.lookup(ACCESOAPLICACION);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarLectura(){
        final Long idPersona = parseLong("111");
        final Long idEmpresa = parseLong("1111");
        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setId(idPersona);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);

        try{
            gestionConcesionAutorizacion.autorizarLectura(pAutorizada, empresa);
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        }
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarOperacion(){
        final Long idPersona = parseLong("111");
        final Long idEmpresa = parseLong("111");
        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setId(idPersona);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);

        try{
            gestionConcesionAutorizacion.autorizarOperacion(pAutorizada, empresa);
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        }
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarLecturaOk(){
        final Long idPersona = parseLong("1");
        final Long idEmpresa = parseLong("1");
        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setId(idPersona);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);

        try{
            gestionConcesionAutorizacion.autorizarLectura(pAutorizada, empresa);
            //ok
        } catch (UsuarioNoEncontradoException e) {
            fail("No debe lanzar una excepcion");
        }
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarOperacioOk(){
        final Long idPersona = parseLong("1");
        final Long idEmpresa = parseLong("1");
        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setId(idPersona);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);

        try{
            gestionConcesionAutorizacion.autorizarOperacion(pAutorizada, empresa);
            //ok
        } catch (UsuarioNoEncontradoException e) {
            fail("No debe lanzar una excepcion");
        }
    }

    @Requisitos({"RF10"})
    @Test
    public void testAccederAplicacion(){
        Usuario user = new Usuario();
        user.setUser("Almu");
        user.setContrasena("1234");

        try{
            gestionAccesoAplicacion.accederAplicacion(user.getUser(), user.getContrasena());
            //ok
        } catch (AccesoException e) {
            fail("No Debe lanzar una excepcion ");
        }
    }

    @Requisitos({"RF10"})
    @Test
    public void testAccederAplicacionErrorUsuario(){
        Usuario user = new Usuario();
        user.setUser("almudena");
        user.setContrasena("1234");

        try{
            gestionAccesoAplicacion.accederAplicacion(user.getUser(), user.getContrasena());
            fail("Debe lanzar una excepcion");
        } catch (AccesoException e) {
            //ok
        }
    }

}
