import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.GestionConcesionAutorizacion;
import ma3s.fintech.Usuario;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

import static java.lang.Long.parseLong;
import static org.junit.Assert.fail;

public class Usuarios {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechTest";

    private static final String CONCESION_AUTORIZACION = "java:global/classes/ConcesionAutorizacion";

    private GestionConcesionAutorizacion gestionConcesionAutorizacion;

    @Before
    public void setup() throws NamingException {
        gestionConcesionAutorizacion = (GestionConcesionAutorizacion) SuiteTest.ctx.lookup(CONCESION_AUTORIZACION);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarLectura(){
        final String nombre = "Salvador";
        final String apellidos = "Ortiz";
        final String identificacion = "12345678S";
        final Long id = parseLong("1", 1);

        try{
            gestionConcesionAutorizacion.autorizarLectura(nombre, apellidos, identificacion, id);
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        }
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarOperacion(){
        final String nombre = "Salvador";
        final String apellidos = "Ortiz";
        final String identificacion = "12345678S";
        final Long id = parseLong("1", 1);

        try{
            gestionConcesionAutorizacion.autorizarOperacion(nombre, apellidos, identificacion, id);
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        }
    }
}
