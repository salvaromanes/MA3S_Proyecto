package ma3s.fintech.test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import org.junit.Before;
import ma3s.fintech.ejb.excepciones.*;
import ma3s.fintech.*;
import org.junit.Test;

import javax.naming.NamingException;

import java.util.Date;

import static java.lang.Long.parseLong;
import static org.junit.Assert.fail;

public class Autorizados {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";
    private static final String ANADIRAUTORIZADOS = "java:global/classes/AnadirAutorizados";
    private static final String ELIMINAR_AUTORIZADOS = "java:global/classes/EliminarAutorizados";
    private static final String MODIFICARPAUTORIZADA = "java:global/classes/ModificarPAutorizada";

    private ma3s.fintech.ejb.GestionEliminarAutorizados gestionEliminarAutorizados;
    private ma3s.fintech.ejb.GestionAnadirAutorizados gestionAnadirAutorizados;
    private ma3s.fintech.ejb.GestionModificarPAutorizada gestionModificarPAutorizada;

    @Before
    public void setup() throws NamingException {
        gestionEliminarAutorizados = (ma3s.fintech.ejb.GestionEliminarAutorizados) SuiteTest.ctx.lookup(ELIMINAR_AUTORIZADOS);
        gestionAnadirAutorizados = (ma3s.fintech.ejb.GestionAnadirAutorizados) SuiteTest.ctx.lookup(ANADIRAUTORIZADOS);
        gestionModificarPAutorizada = (ma3s.fintech.ejb.GestionModificarPAutorizada) SuiteTest.ctx.lookup(MODIFICARPAUTORIZADA);

        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    @Requisitos({"RF8"})
    @Test

    public void testDarBaja(){
        Usuario user = new Usuario();
        user.setUser("Almu");
        user.setContrasena("1234");

        PAutorizada pA = new PAutorizada();
        pA.setId(parseLong("256"));
        pA.setNombre("Salva");
        pA.setIdentificacion("12345678S");
        pA.setId(parseLong("1"));

        Empresa empresa = new Empresa();
        empresa.setId(parseLong("1"));
        empresa.setIdentificacion("UMA");

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), pA.getId(), empresa.getId());
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
        Long id = parseLong("444");
        Long idEmpresa = parseLong("1");

        try{
            gestionEliminarAutorizados.darBaja("Almu", id, idEmpresa);
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

        Long id = parseLong("2");

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");
        empresa.setId(parseLong("1"));

        try{
            gestionEliminarAutorizados.darBaja(user.getUser(), id, empresa.getId());
            fail("Debe lanzar una excepcion NoEsPAutorizadaException");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion NoEsPAutorizadaException" + e.getMessage());
        } catch (NoEsPAutorizadaException e) {
            //ok
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion NoEsPAutorizadaException" + e.getMessage());
        }
    }


    @Requisitos({"RF6"})
    @Test

    public void testAnaPNoEsPersonaA() {

        final String nombre = "Salva";

        PAutorizada aut = new PAutorizada();
        aut.setNombre("Alvaro");
        aut.setId(parseLong("44893"));

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");
        empresa.setId(parseLong("1"));

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, nombre);
            fail("Debe lanzar una excepcion");
        } catch (NoEsPAutorizadaException e) {
            //ok
        } catch (EmpresaNoExistenteException e) {
            fail("Debe lanzar una excepcion tipo NoEsPAutorizadaException ");
        } catch (PersonaNoExisteException e) {
            fail("Debe lanzar una excepcion tipo NoEsPAutorizadaException ");
        } catch (EmpresaNoRelacException e) {
            fail("Debe lanzar una excepcion tipo NoEsPAutorizadaException ");
        } catch (NoEsAdministrativoException e) {
            fail("Debe lanzar una excepcion tipo NoEsPAutorizadaException ");
        }

    }


    @Requisitos({"RF6"})
    @Test

    public void testAnaPNoEsEmpresa() {
        final String nombre = "Salva";
        final Long id = parseLong("1");
        final String emp = "123";
        final Long idEmpre = parseLong("25");

        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);
        aut.setId(id);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);
        empresa.setId(idEmpre);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, nombre);
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
        final Long id = parseLong("1");
        final String emp = "UCAM";
        final Long idEmpre = parseLong("2");


        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);
        aut.setId(id);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);
        empresa.setId(idEmpre);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, "Adrian");
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
        final Long id = parseLong("1");
        final String emp = "UMA";
        final Long idEmpre = parseLong("1");

        Usuario user_aux = new Usuario();
        user_aux.setUser(nombre);

        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);
        aut.setId(id);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);
        empresa.setId(idEmpre);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, nombre);
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
        final Long id = parseLong("1");
        final String emp = "UCAM";
        final Long idEmpre = parseLong("2");


        PAutorizada aut = new PAutorizada();
        aut.setNombre(nombre);
        aut.setId(id);

        Empresa empresa = new Empresa();
        empresa.setIdentificacion(emp);
        empresa.setId(idEmpre);

        try {
            gestionAnadirAutorizados.anadirPAut
                    (aut, empresa, nombre);
            //ok
        } catch (NoEsPAutorizadaException e) {
            fail("NoEsPAutorizadaException "+ e.getMessage());
        } catch (EmpresaNoExistenteException e) {
            fail("EmpresaNoExistenteException "+ e.getMessage());
        } catch (PersonaNoExisteException e) {
            fail("PersonaNoExisteException " + e.getMessage());
        } catch (EmpresaNoRelacException e ) {
            fail("EmpresaNoRelacException "+ e.getMessage());
        } catch (NoEsAdministrativoException e) {
            fail("NoEsAdministrativoException "+ e.getMessage());
        }
    }


    // ------------------------------------TEST MARIO----------------------------------------

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
            gestionModificarPAutorizada.modificarFechaNacimiento(user.getUser(), pAut.getId(), null);
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
            gestionModificarPAutorizada.modificarFechaInicio(user.getUser(), pAut.getId(), null);
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
            gestionModificarPAutorizada.modificarFechaFin(user.getUser(), pAut.getId(), null);
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
            gestionModificarPAutorizada.modificarEstado(user.getUser(), pAut.getId(), null);
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
}
