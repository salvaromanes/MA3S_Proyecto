package ma3s.fintech.test;

import ma3s.fintech.Empresa;
import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import es.uma.informatica.sii.anotaciones.Requisitos;
import static org.junit.Assert.fail;
import javax.naming.NamingException;

public class Clientes {
    private static final String UNIDAD_PERSITENCIA_PRUEBAS = "FintechEjbTest";

    private static final String ALTA_CLIENTE = "java:global/classes/AltaCliente";
    private static final String BAJA_CLIENTE = "java:global/classes/BajaCliente";
    private static final String MODIFICAR_CLIENTE = "java:global/classes/ModificarCliente";

    private ma3s.fintech.ejb.GestionAltaCliente gestionAltaCliente;
    private ma3s.fintech.ejb.GestionBajaCliente gestionBajaCliente;
    private ma3s.fintech.ejb.GestionModificarCliente gestionModificarCliente;

    @Before
    public void setup() throws NamingException {
        gestionAltaCliente = (ma3s.fintech.ejb.GestionAltaCliente) SuiteTest.ctx.lookup(ALTA_CLIENTE);
        gestionBajaCliente = (ma3s.fintech.ejb.GestionBajaCliente) SuiteTest.ctx.lookup(BAJA_CLIENTE);
        gestionModificarCliente = (ma3s.fintech.ejb.GestionModificarCliente) SuiteTest.ctx.lookup(MODIFICAR_CLIENTE);


        BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    }

    //------------------Alta cliente
    //Empresas test
    @Requisitos({"RF2"})
    @Test

    public void testDarAltaEmpresa(){
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setId(new Long("81"));
        nuevaEmpresa.setIdentificacion("ES458");
        nuevaEmpresa.setTipoCliente("Jurídico");
        nuevaEmpresa.setEstado("Abierta");
        nuevaEmpresa.setFechaAlta(new Date());
        nuevaEmpresa.setDireccion("Bulevaur");
        nuevaEmpresa.setCiudad("Málaga");
        nuevaEmpresa.setCodigopostal("4562");
        nuevaEmpresa.setPais("Spain");
        nuevaEmpresa.setRazonSocial("ES4558");

        try {
            gestionAltaCliente.darAltaEmpresa(nuevaEmpresa);
        }catch (CampoVacioException | ClienteYaExistenteException e){
            fail("Lanzó excepción al insertar");
        }

        //try catch comprobando el elemento
    }

    @Requisitos({"RF2"})
    @Test
    public void testDarAltaEmpresaCampoVacio(){
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setIdentificacion("ES458");
        nuevaEmpresa.setTipoCliente("Jurídico");
        nuevaEmpresa.setDireccion("Bulevaur");
        nuevaEmpresa.setPais("Spain");
        nuevaEmpresa.setRazonSocial("ES4558");

        try {
            gestionAltaCliente.darAltaEmpresa(nuevaEmpresa);
            fail("Debe de lanzar excepción");
        }catch (CampoVacioException e){

        }catch (ClienteYaExistenteException e){
            fail("Debe lanzar otra excepción");
        }

        //try catch comprobando el elemento
    }

    @Requisitos({"RF2"})
    @Test

    public void testDarAltaEmpresaClienteYaExistente(){
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setId(new Long(83));
        nuevaEmpresa.setIdentificacion("ES450");
        nuevaEmpresa.setTipoCliente("Jurídico");
        nuevaEmpresa.setEstado("Abierta");
        nuevaEmpresa.setFechaAlta(new Date());
        nuevaEmpresa.setDireccion("Bulevaur");
        nuevaEmpresa.setCiudad("Málaga");
        nuevaEmpresa.setCodigopostal("4562");
        nuevaEmpresa.setPais("Spain");
        nuevaEmpresa.setRazonSocial("ES4558");

        try {
            gestionAltaCliente.darAltaEmpresa(nuevaEmpresa);
        }catch (CampoVacioException e){
            fail("Lanzó excepción al insertar");
        }catch (ClienteYaExistenteException e){

        }

        //try catch comprobando el elemento
    }

//Individual test
    @Requisitos({"RF2"})
    @Test
    public void testDarAltaIndividual(){
        Individual individual = new Individual();
        individual.setId(new Long(82));
        individual.setIdentificacion("MAN82");
        individual.setTipoCliente("Personal");
        individual.setEstado("Abierto");
        individual.setFechaAlta(new Date());
        individual.setDireccion("Bulevaur");
        individual.setCiudad("Málaga");
        individual.setCodigopostal("4562");
        individual.setPais("Spain");
        individual.setNombre("MANOLO");
        individual.setApellido("García");

        try {
            gestionAltaCliente.darAltaIndividual(individual);
        }catch (CampoVacioException|ClienteYaExistenteException e){
            fail("Lanzó excepción al insertar");
        }

        //try catch comprobando el elemento
    }

    @Requisitos({"RF2"})
    @Test
    public void testDarAltaIndividualCampoVacio(){
        Individual individual = new Individual();
        individual.setIdentificacion("MAN45");
        individual.setTipoCliente("Personal");
        individual.setDireccion("Bulevaur");
        individual.setCiudad("Málaga");
        individual.setPais("Spain");
        individual.setApellido("García");

        try {
            gestionAltaCliente.darAltaIndividual(individual);
            fail("Debe de lanzar excepción");
        }catch (CampoVacioException e){

        }catch (ClienteYaExistenteException e){
            fail("Debe de lanzar otra excepción");
        }

        //try catch comprobando el elemento
    }

    @Requisitos({"RF2"})
    @Test
    public void testDarAltaIndividualClienteYaExistente(){
        Individual individual = new Individual();
        individual.setId(new Long(85));
        individual.setIdentificacion("MAN45");
        individual.setTipoCliente("Personal");
        individual.setEstado("Abierto");
        individual.setFechaAlta(new Date());
        individual.setDireccion("Bulevaur");
        individual.setCiudad("Málaga");
        individual.setCodigopostal("4562");
        individual.setPais("Spain");
        individual.setNombre("MANOLO");
        individual.setApellido("García");

        try {
            gestionAltaCliente.darAltaIndividual(individual);
            fail("Debe lanzar una excepcion");
        }catch (CampoVacioException e){
            fail("Lanzó excepción al insertar");
        }catch (ClienteYaExistenteException e){

        }

        //try catch comprobando el elemento
    }

    //-------------------------Modificar Cliente

    //Empresa test

    //Direccion
    @Requisitos({"RF3"})
    @Test
    public void testModDireccionEmpresa(){
        try{
            gestionModificarCliente.modDireccionEmpresa(new Long (20),"Penelope");
        }catch(CampoVacioException|EmpresaNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModDireccionEmpresaCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modDireccionEmpresa(new Long(20),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(EmpresaNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModDireccionEmpresaNoExistente(){
        try{
            gestionModificarCliente.modDireccionEmpresa(new Long(300),"Penelope");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(EmpresaNoExistenteException e){
            //ok
        }
    }

    //Ciudad

    @Requisitos({"RF3"})
    @Test
    public void testModCiudadEmpresa(){
        try{
            gestionModificarCliente.modCiudadEmpresa(new Long (20),"Madrid");
        }catch(CampoVacioException|EmpresaNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModCiudadEmpresaCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modCiudadEmpresa(new Long(20),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(EmpresaNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModCiudadEmpresaNoExistente(){
        try{
            gestionModificarCliente.modCiudadEmpresa(new Long(300),"Madrid");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(EmpresaNoExistenteException e){
            //ok
        }
    }

    //Codigo postal

    @Requisitos({"RF3"})
    @Test
    public void testModCodigoPostalEmpresa(){
        try{
            gestionModificarCliente.modCodigoPostalEmpresa(new Long(20),"45621");
        }catch(CampoVacioException|EmpresaNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModCodigoPostalCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modCodigoPostalEmpresa(new Long(20),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(EmpresaNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Test
    public void testModCodigoPostalNoExistente(){
        try{
            gestionModificarCliente.modCodigoPostalEmpresa(new Long(300),"45621");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(EmpresaNoExistenteException e){
            //ok
        }
    }

    //Pais
    @Requisitos({"RF3"})
    @Test
    public void testModPaisEmpresa(){
        try{
            gestionModificarCliente.modPaisEmpresa(new Long(20),"England");
        }catch(CampoVacioException|EmpresaNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModPaisEmpresaCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modPaisEmpresa(new Long(20),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(EmpresaNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModPaisEmpresaNoExistente(){
        try{
            gestionModificarCliente.modPaisEmpresa(new Long(300),"England");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(EmpresaNoExistenteException e){
            //ok
        }
    }

    //Razon social
    @Requisitos({"RF3"})
    @Test
    public void testModRazonSocialEmpresa(){
        try{
            gestionModificarCliente.modRazonSocialEmpresa(new Long(20),"ES987");
        }catch(CampoVacioException|EmpresaNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModRazonSocialEmpresaCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modRazonSocialEmpresa(new Long(20),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(EmpresaNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModRazonSocialEmpresaNoExistente(){
        try{
            gestionModificarCliente.modRazonSocialEmpresa(new Long(300),"ES987");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(EmpresaNoExistenteException e){
            //ok
        }
    }

    //-----------------Individual
    //Direccion
    @Requisitos({"RF3"})
    @Test
    public void testModDireccionIndividual(){
        try{
            gestionModificarCliente.modDireccionIndividual(new Long(30),"Miraflores");
        }catch(CampoVacioException|IndividualNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModDireccionIndividualCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modDireccionIndividual(new Long(30),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(IndividualNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModDireccionIndividualIndividualNoExistenteException(){
        try{
            gestionModificarCliente.modDireccionIndividual(new Long(400),"Miraflores");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(IndividualNoExistenteException e){
            //ok
        }
    }

    //Ciudad

    @Requisitos({"RF3"})
    @Test
    public void testModCiudadIndividual(){
        try{
            gestionModificarCliente.modCiudadIndividual(new Long(30),"Barcelona");
        }catch(CampoVacioException|IndividualNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModCiudadIndividualCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modCiudadIndividual(new Long(30),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(IndividualNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModCiudadIndividualIndividualNoExistenteException(){
        try{
            gestionModificarCliente.modCiudadIndividual(new Long(400),"Barcelona");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(IndividualNoExistenteException e){
            //ok
        }
    }

    //Codigo Postal
    @Requisitos({"RF3"})
    @Test
    public void testModCodigoPostalIndividual(){
        try{
            gestionModificarCliente.modCodigoPostalIndividual(new Long(30),"29563");
        }catch(CampoVacioException|IndividualNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModCodigoPostalIndividualCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modCodigoPostalIndividual(new Long(30),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(IndividualNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModCodigoPostalIndividualIndividualNoExistenteException(){
        try{
            gestionModificarCliente.modCodigoPostalIndividual(new Long(400),"29563");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(IndividualNoExistenteException e){
            //ok
        }
    }

    //Pais
    @Requisitos({"RF3"})
    @Test
    public void testModPaisIndividual(){
        try{
            gestionModificarCliente.modPaisIndividual(new Long(30),"Germany");
        }catch(CampoVacioException|IndividualNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModPaisIndividualCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modPaisIndividual(new Long(30),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(IndividualNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModPaisIndividualIndividualNoExistenteException(){
        try{
            gestionModificarCliente.modPaisIndividual(new Long(400),"Germany");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(IndividualNoExistenteException e){
            //ok
        }
    }

    //Nombre
    @Requisitos({"RF3"})
    @Test
    public void testModNombreIndividual(){
        try{
            gestionModificarCliente.modNombreIndividual(new Long(30),"Paco");
        }catch(CampoVacioException|IndividualNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModNombreIndividualCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modNombreIndividual(new Long(30),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(IndividualNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModNombreIndividualIndividualNoExistenteException(){
        try{
            gestionModificarCliente.modNombreIndividual(new Long(400),"Paco");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(IndividualNoExistenteException e){
            //ok
        }
    }

    //Apellido
    @Requisitos({"RF3"})
    @Test
    public void testModApellidoIndividual(){
        try{
            gestionModificarCliente.modApellidoIndividual(new Long(30),"Suarez");
        }catch(CampoVacioException|IndividualNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModApellidoIndividualCampoVacio(){
        String iden = null;
        try{
            gestionModificarCliente.modApellidoIndividual(new Long(30),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(IndividualNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModApellidoIndividualIndividualNoExistenteException(){
        try{
            gestionModificarCliente.modApellidoIndividual(new Long(400),"Suarez");
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(IndividualNoExistenteException e){
            //ok
        }
    }

    //Fecha nacimiento
    @Requisitos({"RF3"})
    @Test
    public void testModFechaNacimientoIndividual(){
        try{
            gestionModificarCliente.modFechaNacimientoIndividual(new Long(30), new Date());
        }catch(CampoVacioException|IndividualNoExistenteException e){
            fail("Lanzó excepción al modificar");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModFechaNacimientoIndividualCampoVacio(){
        Date iden = null;
        try{
            gestionModificarCliente.modFechaNacimientoIndividual(new Long(30),iden);
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            //ok
        }catch(IndividualNoExistenteException e){
            fail("Debe lanzar la excepción de campo vacio");
        }
    }

    @Requisitos({"RF3"})
    @Test
    public void testModFechaNacimientoIndividualIndividualNoExistenteException(){
        try{
            gestionModificarCliente.modFechaNacimientoIndividual(new Long(400),new Date());
            fail("Debe de lanzar una excepción");
        }catch(CampoVacioException e){
            fail("Debe lanzar la excepción empresa no existente");
        }catch(IndividualNoExistenteException e){
            //ok
        }
    }

//-------------------Dar baja
    @Requisitos({"RF4"})
    @Test
    public void testDarBajaCliente(){
        try{
            gestionBajaCliente.darBajaCliente(new Long(40));
        }catch (CampoVacioException|CuentaAbiertaException| ClienteNoExisteException e){
            fail("No debe lanzar excepción");
        }
    }

    @Requisitos({"RF4"})
    @Test
    public void testDarBajaClienteCampoVacio(){
        Long id = null;
        try{
            gestionBajaCliente.darBajaCliente(id);
            fail("Debe lanzar excepcion");
        }catch (CuentaAbiertaException| ClienteNoExisteException e){
            fail("Debe lanzar campovacio excepcion");
        }catch (CampoVacioException e){

        }
    }

    @Requisitos({"RF4"})
    @Test
    public void testDarBajaClienteCuentaAbierta(){
        try{
            gestionBajaCliente.darBajaCliente(new Long(450));
            fail("Debe lanzar excepcion");
        }catch (CampoVacioException| ClienteNoExisteException e){
            fail("No debe lanzar excepción");
        }catch (CuentaAbiertaException e){

        }
    }

    @Requisitos({"RF4"})
    @Test
    public void testDarBajaClienteClienteNoExiste(){
        try{
            gestionBajaCliente.darBajaCliente(new Long(400));
            fail("Debe lanzar excepcion");
        }catch (CampoVacioException|CuentaAbiertaException e){
            fail("No debe lanzar excepción");
        }catch (ClienteNoExisteException e){

        }
    }
}
