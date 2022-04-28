package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionInfHolanda;
import ma3s.fintech.ejb.excepciones.*;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;

import static java.lang.Long.parseLong;
import static org.junit.Assert.fail;

public class Reportes {

    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";
    private static final String CUENTASAPI = "java:global/classes/GeneracionInfHolanda";

    private ma3s.fintech.ejb.GestionInfHolanda gestionInfHolanda;

    @Before
    public void setup() throws NamingException {
        gestionInfHolanda = (ma3s.fintech.ejb.GestionInfHolanda) SuiteTest.ctx.lookup(CUENTASAPI);

        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }


    @Requisitos({"RF11"})
    @Test

    public void testGestionInfNoValido(){
      final String iban = "123456789012345";
      //final String ref_iban = "8732";

        Segregada seg = new Segregada();
        seg.setIban(iban);

        Referencia ref = new Referencia();
        ref.setIban(null);

        try {
            gestionInfHolanda.CuentasApi(seg);
            fail("Debe lanzar una excepcion CuentaNoExistenteException");
        }catch (CuentaNoExistenteException e) {
            //ok
        }

    }

    @Requisitos({"RF11"})
    @Test

    public void testGestionInfValido(){
//        final String iban = "8732";
//        //final String ref_iban = "8732";
//
//
//        Segregada seg = new Segregada();
//        seg.setIban(iban);
//
//      //  Referencia ref = new Referencia();
//       // ref.setIban(ref_iban);
//        try {
//            gestionInfHolanda.CuentasApi(seg);
//            //ok
//        }catch (CuentaNoExistenteException e) {
//            fail("No Debe lanzar una excepcion");
//        }

    }

    @Requisitos({"RF11"})
    @Test

    public void testGestionClienteApiError(){
        final String nombre = "Juan";
        final Long id = parseLong("54");

        Cliente c1 = new Cliente();
        c1.setIdentificacion(nombre);
        c1.setId(id);

        try {
            gestionInfHolanda.ClienteApi(c1);
            fail("Debe lanzar una excepcion ClienteNoExisteException");
        }catch (ClienteNoExisteException e) {
            //ok
        }
    }

    @Requisitos({"RF11"})
    @Test

    public void testGestionClienteApi(){
        final String nombre = "987654321A";
        final Long id = parseLong("223");

        Cliente c1 = new Cliente();
        c1.setIdentificacion(nombre);
        c1.setId(id);

        try {
            gestionInfHolanda.ClienteApi(c1);
            //ok
        }catch (ClienteNoExisteException e) {
            fail("No Debe lanzar una excepcion");
        }
    }

    @Requisitos({"RF11"})
    @Test

    public void testPAutoError(){
        final String nombre = "Juan";
        final Long id = parseLong("12");
        String aux = "";

        PAutorizada autorizada = new PAutorizada();
        autorizada.setId(id);
        autorizada.setNombre(nombre);

        try {
            aux = gestionInfHolanda.PAutorApi(autorizada);
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        }catch (NoEsPAutorizadaException e) {
            //ok
        }
    }

    @Requisitos({"RF11"})
    @Test

    public void testPAuto(){
        final String nombre = "Salva";
        final Long id = parseLong("1");
        String aux = "";

        PAutorizada autorizada = new PAutorizada();
        autorizada.setId(id);
        autorizada.setNombre(nombre);

        try {
            aux = gestionInfHolanda.PAutorApi(autorizada);
            //ok
        }catch (NoEsPAutorizadaException e) {
            fail("No Debe lanzar una excepcion");
        }

    }





}
