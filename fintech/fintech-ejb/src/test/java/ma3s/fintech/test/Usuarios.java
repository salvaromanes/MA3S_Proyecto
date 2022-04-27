package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.ejb.GestionConcesionAutorizacion;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

import static java.lang.Long.parseLong;
import static org.junit.Assert.fail;

public class Usuarios {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String CONCESION_AUTORIZACION = "java:global/classes/ConcesionAutorizacion";
    private static final String eliminarAutorizados = "java:global/classes/EliminarAutorizados";

    private ma3s.fintech.ejb.GestionConcesionAutorizacion gestionConcesionAutorizacion;
    private ma3s.fintech.ejb.GestionEliminarAutorizados gestionEliminarAutorizados;

    @Before
    public void setup() throws NamingException {
        gestionConcesionAutorizacion = (ma3s.fintech.ejb.GestionConcesionAutorizacion) SuiteTest.ctx.lookup(CONCESION_AUTORIZACION);
        gestionEliminarAutorizados = (ma3s.fintech.ejb.GestionEliminarAutorizados) SuiteTest.ctx.lookup(eliminarAutorizados);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarLectura(){
        final Long idPersona = parseLong("1");
        final Long idEmpresa = parseLong("1");
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);

        try{
            gestionConcesionAutorizacion.autorizarLectura(idPersona, empresa);
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        }
    }

    @Requisitos({"RF15"})
    @Test
    public void testAutorizarOperacion(){
        final Long idPersona = parseLong("1");
        final Long idEmpresa = parseLong("1");
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);

        try{
            gestionConcesionAutorizacion.autorizarOperacion(idPersona, empresa);
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        }
    }
/*
    @Requisitos({"RF10"})
    @Test
    public void testDarBaja(){
        Cliente cliente = new Cliente();

        try{

        } catch ( ) {
            //ok
        }
    }
  */
}
