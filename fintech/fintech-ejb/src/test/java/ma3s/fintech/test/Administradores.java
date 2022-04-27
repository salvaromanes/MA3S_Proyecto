package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import org.junit.Before;
import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;
import org.junit.Test;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.Date;
import static java.lang.Long.parseLong;
import static org.junit.Assert.fail;

public class Administradores {
    private static final String UNIDAD_PERSISTENCIA_PRUEBAS = "FintechEjbTest";

    private static final String ACCESO_PERSONAL = "java:global/classes/AccesoPersonal";
    private static final String ANADIR_AUTORIZADOS = "java:global/classes/AnadirAutorizados";
    private static final String GENERAR_CSV = "java:global/classes/GenerarCSV";
    private static final String MODIFICAR_PAUTORIZADA = "java:global/classes/ModificarPAutorizada";
    private static final String ELIMINAR_AUTORIZADOS = "java:global/classes/EliminarAutorizados";

    private ma3s.fintech.ejb.GestionAccesoPersonal gestionAccesoPersonal;
    private ma3s.fintech.ejb.GestionAnadirAutorizados gestionAnadirAutorizados;
    private ma3s.fintech.ejb.GestionGenerarCSV gestionGenerarCSV;
    private ma3s.fintech.ejb.GestionModificarPAutorizada gestionModificarPAutorizada;
    private ma3s.fintech.ejb.GestionEliminarAutorizados gestionEliminarAutorizados;

    @Before
    public void setup() throws NamingException {
        gestionAccesoPersonal = (ma3s.fintech.ejb.GestionAccesoPersonal) SuiteTest.ctx.lookup(ACCESO_PERSONAL);
        gestionAnadirAutorizados = (ma3s.fintech.ejb.GestionAnadirAutorizados) SuiteTest.ctx.lookup(ANADIR_AUTORIZADOS);
        gestionGenerarCSV = (ma3s.fintech.ejb.GestionGenerarCSV) SuiteTest.ctx.lookup(GENERAR_CSV);
        gestionModificarPAutorizada = (ma3s.fintech.ejb.GestionModificarPAutorizada) SuiteTest.ctx.lookup(MODIFICAR_PAUTORIZADA);
        gestionEliminarAutorizados = (ma3s.fintech.ejb.EliminarAutorizados) SuiteTest.ctx.lookup(ELIMINAR_AUTORIZADOS);
        BaseDatos.inicializaBaseDatos(UNIDAD_PERSISTENCIA_PRUEBAS);
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
            //ok
        } catch (IOException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (PersonaNoExisteException e) {
            fail("No Debe lanzar una excepcion ");
        }

        try {
            gestionGenerarCSV.generarCSV(user.getUser(), "Periodico", d);
            //ok
        } catch (IOException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (PersonaNoExisteException e) {
            fail("No Debe lanzar una excepcion ");
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
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (IOException e) {
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion");
            //ok
        }

        try {
            gestionGenerarCSV.generarCSV(user.getUser(), "Periodico", d);
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (IOException e) {
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion");
            //ok
        }
    }



    // ------------------------------------TEST MARIO----------------------------------------

    // Modificacion del campo IDENTIFICACION

    @Requisitos({"RF7"})
    @Test

    public void testModifyIdentPAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("2"));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "ident");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyIdentPAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "ident");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyIdentPAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyIdentPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "123");
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado

    }

    // Modificacion del campo Nombre

    @Requisitos({"RF7"})
    @Test

    public void testModifyNamePAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarNombre(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyNamePAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarNombre(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyNamePAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarNombre(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyNamePAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarNombre(user.getUser(), pAut.getId(), "Leonardo");
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado

    }

    // Modificar del campo Apellidos

    @Requisitos({"RF7"})
    @Test

    public void testModifySurNamePAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarApellidos(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifySurNamePAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarApellidos(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifySurNamePAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarApellidos(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifySurNamePAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarApellidos(user.getUser(), pAut.getId(), "Jirafa");
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado
    }

    // Modificacion del campo Direccion

    @Requisitos({"RF7"})
    @Test

    public void testModifyAdressPAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarDireccion(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyAdressPAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarDireccion(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyAdressPAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarDireccion(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyAdressPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarDireccion(user.getUser(), pAut.getId(), "Calle Hola");
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado
    }

    // Modificacion del campo Fecha de Nacimiento

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaNacimientoPAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaNacimiento(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaNacimientoPAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaNacimiento(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaNacimientoPAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaNacimiento(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaNacimientoPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaNacimiento(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado
    }

    // Modificacion del campo Fecha de Inicio

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaInicioPAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaInicio(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaInicioPAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaInicio(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaInicioPAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaInicio(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaInicioPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaInicio(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado
    }

    // Modificacion del campo Fecha de Fin

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaFinPAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaFin(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaFinPAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaFin(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaFinPAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaFin(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyFechaFinPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarFechaFin(user.getUser(), pAut.getId(), date);
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado
    }

    // Modificacion del campo Estado

    @Requisitos({"RF7"})
    @Test

    public void testModifyEstadoPAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarEstado(user.getUser(), pAut.getId(), "activo");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e){
            // ok -> all is ok
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }catch (EstadoNoValidoException e){
            fail("Se ha capturado EstadoNoValidoException y se esperaba PersonaNoExisteException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyEstadoPAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarEstado(user.getUser(), pAut.getId(), "activo");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException e){
            // ok -> all is ok
        }catch (EstadoNoValidoException e){
            fail("Se ha capturado EstadoNoValidoException y se esperaba NoEsAdministradorException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyEstadoPAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        Date date = new Date(2022/04/27);

        try {
            gestionModificarPAutorizada.modificarEstado(user.getUser(), pAut.getId(), "activo");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException e){
            // ok -> all is ok
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }catch (EstadoNoValidoException e){
            fail("Se ha capturado EstadoNoValidoException y se esperaba CampoVacioException");
        }
    }

    @Requisitos({"RF7"})
    @Test

    public void testModifyEstadoPAutorizadaEstadoNoValido() {
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarEstado(user.getUser(), pAut.getId(), "patata");
            // Si no devuelve nada
            fail("No se ha capturado excepcion y se esperaba EstadoNoValidoException");
        } catch (PersonaNoExisteException e) {
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e) {
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e) {
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        } catch (EstadoNoValidoException e) {
            // ok
        }
    }

    @Requisitos({"RF7"})
    @Test
    public void testModifyEstadoPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1"));

        try {
            gestionModificarPAutorizada.modificarEstado(user.getUser(), pAut.getId(), "activo");
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException e){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException e){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException e){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }catch (EstadoNoValidoException e){
            fail("Se ha capturado EstadoNoValidoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado
    }

    // ----------------------------------------------------------------------------------------------------------


    @Requisitos({"RF8"})
    @Test

    public void testDarBaja(){
        Usuario user = new Usuario();
        user.setUser("Almu");

        PAutorizada pA = new PAutorizada();
        pA.setNombre("Salva");
        pA.setIdentificacion("12345678S");
        pA.setId(parseLong("1"));

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), pA.getId(), empresa.getId());
            //ok
        } catch (PersonaNoExisteException e) {
            fail("No Debe lanzar una excepcion ");
        } catch (NoEsPAutorizadaException e) {
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

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), pA.getId(), empresa.getId());
            fail("Debe lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException e) {
            //ok
        } catch (NoEsPAutorizadaException e) {
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
            gestionEliminarAutorizados.darBaja(user.getUser(), pA.getId(), empresa.getId());
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        } catch (NoEsPAutorizadaException e) {
            //ok
        }
    }
}
