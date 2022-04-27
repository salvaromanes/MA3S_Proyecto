package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionInfHolanda;
import ma3s.fintech.ejb.excepciones.*;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

import static org.junit.Assert.fail;

public class Reportes {


    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String CuentasApi = "java:global/classes/GeneracionInfHolanda";



    private ma3s.fintech.ejb.GestionInfHolanda gestionInfHolanda;



    @Before
    public void setup() throws NamingException {

        gestionInfHolanda = (GestionInfHolanda) SuiteTest.ctx.lookup(CuentasApi);

        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }


    @Requisitos({"RF11"})
    @Test

    public void testGestionInfNoValido(){
        final String iban = "1233223";


        Segregada seg = new Segregada();
        seg.setIban(iban);

        try {
            gestionInfHolanda.CuentasApi(seg);
            fail("Debe lanzar una excepcion");
        }catch (CuentaNoExistenteException e) {
            //ok
        }

    }


    @Requisitos({"RF11"})
    @Test

    public void testGestionInfValido(){
        final String iban = "123";


        Segregada seg = new Segregada();
        seg.setIban(iban);

        try {
            gestionInfHolanda.CuentasApi(seg);
            //ok
        }catch (CuentaNoExistenteException e) {
            fail("Debe lanzar una excepcion");
        }

    }



    @Requisitos({"RF11"})
    @Test

    public void testGestionClienteApiError(){
        final String nombre = "Juan";

        Cliente c1 = new Cliente();
        c1.setIdentificacion(nombre);

        try {
            gestionInfHolanda.ClienteApi(c1);
            fail("Debe lanzar una excepcion");
        }catch (ClienteNoExisteException e) {
            //ok
        }

    }



    @Requisitos({"RF11"})
    @Test

    public void testGestionClienteApi(){
        final String nombre = "987654321A";

        Cliente c1 = new Cliente();
        c1.setIdentificacion(nombre);

        try {
            gestionInfHolanda.ClienteApi(c1);
            //ok
        }catch (ClienteNoExisteException e) {
            fail("Debe lanzar una excepcion");        }

    }






    @Requisitos({"RF11"})
    @Test

    public void testPAutoError(){
        final String nombre = "Juan";

        PAutorizada autorizada = new PAutorizada();
        autorizada.setNombre(nombre);

        try {
            gestionInfHolanda.PAutorApi(autorizada);
            fail("Debe lanzar una excepcion");
        }catch (NoEsPAutorizadaException e) {
            //ok
        }

    }


    @Requisitos({"RF11"})
    @Test

    public void testPAuto(){
        final String nombre = "Salva";

        PAutorizada autorizada = new PAutorizada();
        autorizada.setNombre(nombre);

        try {
            gestionInfHolanda.PAutorApi(autorizada);
            //ok
        }catch (NoEsPAutorizadaException e) {
            fail("Debe lanzar una excepcion");
        }

    }





}
