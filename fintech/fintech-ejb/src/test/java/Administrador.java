import es.uma.informatica.sii.anotaciones.Requisitos;
import ma3s.fintech.*;
import ma3s.fintech.excepciones.*;
import org.junit.Before;
import org.junit.Test;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.fail;

public class Administrador {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String APERTURA_CUENTA = "java:global/classes/AperturaCuenta";
    private static final String CIERRE_CUENTA = "java:global/classes/CierreCuenta";
    private static final String AccesoPersonal = "java:global/classes/AccesoPersonal";
    private static final String AnadirAutorizado = "java:global/classes/AnadirAutorizados";
    private static final String GenerarCSV = "java:global/classes/GenerarCSV";


    private GestionAperturaCuenta gestionAperturaCuenta;
    private GestionCierreCuenta gestionCierreCuenta;
    private GestionAccesoPersonal gestionAccesoPersonal;
    private GestionAnadirAutorizados gestionAnadirAutorizados;
    private GestionGenerarCSV gestionGenerarCSV;


    @Before
    public void setup() throws NamingException {
        gestionAperturaCuenta = (GestionAperturaCuenta) SuiteTest.ctx.lookup(APERTURA_CUENTA);
        gestionCierreCuenta = (GestionCierreCuenta) SuiteTest.ctx.lookup(CIERRE_CUENTA);
        gestionAccesoPersonal = (GestionAccesoPersonal) SuiteTest.ctx.lookup(AccesoPersonal);
        gestionAnadirAutorizados = (GestionAnadirAutorizados) SuiteTest.ctx.lookup(AnadirAutorizado);
        gestionGenerarCSV = (GestionGenerarCSV) SuiteTest.ctx.lookup(GenerarCSV);

        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmNoEncontrado() {
        final String nomb = "alv";
        final String cont = "123";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            //ok
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        } catch (ContraseñaIncorrectaException e) {
            fail("Debe lanzar una excepcion de UsuarioNoEncontrado");
        }
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmNoValido() {
        final String nomb = "MA3S";
        final String cont = "123";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        } catch (UsuarioIncorrectoException e) {
            //ok
        } catch (ContraseñaIncorrectaException e) {
            fail("Debe lanzar una excepcion de UsuarioIncorrecto");
        }
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmContNoValida() {
        final String nomb = "Salva";
        final String cont = "123";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            fail("Debe lanzar una excepcion");
        } catch (UsuarioNoEncontradoException e) {
            fail("Debe lanzar una excepcion de ContasenaIncorrecta");
        } catch (UsuarioIncorrectoException e) {
            fail("Debe lanzar una excepcion de ContasenaIncorrecta");
        } catch (ContraseñaIncorrectaException e) {
            //ok;
        }
    }


    @Requisitos({"RF1"})
    @Test

    public void testAccesoPersonalAdmValido() {
        final String nomb = "Salva";
        final String cont = "malaga";
        Usuario user1 = new Usuario();
        user1.setUser(nomb);
        user1.setContrasena(cont);

        try {
            gestionAccesoPersonal.obtenerPersonal(user1.getUser(), user1.getContrasena());
            //ok
        } catch (UsuarioNoEncontradoException e) {
            fail("NO Debe lanzar una excepcion ");
        } catch (UsuarioIncorrectoException e) {
            fail("NO Debe lanzar una excepcion ");
        } catch (ContraseñaIncorrectaException e) {
            fail("NO Debe lanzar una excepcion ");
        }
    }


    @Requisitos({"RF6"})
    @Test

    public void testAdminNoExiste() {
        final String nombre = "Alvaro";
        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        try {
            gestionAnadirAutorizados.comprobarAdministrador(user_aux.getUser());
            fail("Debe lanzar una excepcion");
        } catch (PersonaNoExisteException e) {
            //ok
        } catch (NoEsAdministrativoException e) {
            fail("Debe lanzar una excepcion de PersonaNoExistente");
        }

    }


    @Requisitos({"RF6"})
    @Test

    public void testAnaPNoEsPersonaA() {
        final String nombre = "Salva";
        final String emp = "123";

        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, user_aux.getUser());
            fail("Debe lanzar una excepcion");
        } catch (NoEsPAutorizadaException e) {
            //ok
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersonaAut ");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersonaAut ");
        } catch (EmpresaNoRelacException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersonaAut ");
        } catch (NoEsAdministrativoException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersonaAut ");
        }

    }


    @Requisitos({"RF6"})
    @Test

    public void testAnaPNoEsEmpresa() {
        final String nombre = "Salva";
        final String emp = "123";

        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, user_aux.getUser());
            fail("Debe lanzar una excepcion");
        } catch (NoEsPAutorizadaException e) {
            fail("Debe lanzar una excepcion tipo NoEsEmp ");
        } catch (EmpresaNoExistenteException e) {
            //ok
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion tipo NoEsEmp ");
        } catch (EmpresaNoRelacException e) {
            fail("Debe lanzar una excepcion tipo NoEsEmp ");

        } catch (NoEsAdministrativoException e) {
            fail("Debe lanzar una excepcion tipo NoEsEmp ");
        }

    }


    @Requisitos({"RF6"})
    @Test

    public void testAnaPersonaNoExistente() {
        final String nombre = "Salva";
        final String emp = "123";

        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, user_aux.getUser());
            fail("Debe lanzar una excepcion");
        } catch (NoEsPAutorizadaException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersona ");
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersona ");
        } catch (PersonaNoExisteException e) {
            //ok
        } catch (EmpresaNoRelacException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersona ");

        } catch (NoEsAdministrativoException e) {
            fail("Debe lanzar una excepcion tipo NoEsPersona ");
        }

    }


    @Requisitos({"RF6"})
    @Test

    public void testAnaEmpresaNoRelac() {
        final String nombre = "Salva";
        final String emp = "123";

        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, user_aux.getUser());
            fail("Debe lanzar una excepcion");
        } catch (NoEsPAutorizadaException e) {
            fail("Debe lanzar una excepcion tipo EmpresaNoRelac ");
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion tipo EmpresaNoRelac ");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion tipo EmpresaNoRelac ");
        } catch (EmpresaNoRelacException e) {
            //ok
        } catch (NoEsAdministrativoException e) {
            fail("Debe lanzar una excepcion tipo EmpresaNoRelac ");
        }

    }

    @Requisitos({"RF6"})
    @Test

    public void testAnaEmpresaCorrecto() {
        final String nombre = "Salva";
        final String emp = "UMA";

        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, user_aux.getUser());
            //ok
        } catch (NoEsPAutorizadaException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (EmpresaNoExistenteException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (PersonaNoExisteException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (EmpresaNoRelacException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (NoEsAdministrativoException e) {
            fail("No Debe lanzar una excepcion ");
        }
    }


    @Requisitos({"RF12"})
    @Test

    public void testGenerarCSV() {
        Date d = new Date(2022 / 03 / 10);

        Usuario user = new Usuario();
        user.setUser("AlmudenaUser");
        user.setEsAdmin(true);

        try {
            gestionGenerarCSV.generarCSV(user.getUser(), "Individual", d);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersonaNoExisteException e) {
            e.printStackTrace();
        }

        try {
            gestionGenerarCSV.generarCSV(user.getUser(), "Periodico", d);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersonaNoExisteException e) {
            e.printStackTrace();
        }

    }


    @Requisitos({"RF12"})
    @Test

    public void testGenerarCSVfallo() {
        Date d = new Date(2022 / 03 / 10);

        Usuario user = new Usuario();
        user.setUser("AlmudenaUser");
        user.setEsAdmin(false);

        try {
            gestionGenerarCSV.generarCSV(user.getUser(), "Individual", d);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersonaNoExisteException e) {
            e.printStackTrace();
        }

        try {
            gestionGenerarCSV.generarCSV(user.getUser(), "Periodico", d);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PersonaNoExisteException e) {
            e.printStackTrace();
        }
    }


}
