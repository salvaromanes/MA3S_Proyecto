package ma3s.fintech;

import ma3s.fintech.excepciones.CampoVacioException;
import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.excepciones.PersonaNoExisteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import static java.lang.Long.parseLong;

public class ModificarPAutorizada implements GestionModificarPAutorizada{

    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    @Override
    public void modificarIdentificacion(Long id_adm, Long id_aut, String identNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(identNew == null){
            throw new CampoVacioException("El campo (identificación) a actualizar no contiene información.");
        }

        pAutorizada.setIdentificacion(identNew);
        em.merge(pAutorizada);
    }

    // ---------------------------------------------------------------------

    public void testModifyIdentPAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "ident");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException){
            // ok -> all is ok
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    public void testModifyIdentPAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "ident");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException){
            // ok -> all is ok
        }
    }

    public void testModifyIdentPAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException){
            // ok -> all is ok
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    public void testModifyIdentPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "123");
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado

    }

    // ---------------------------------------------------------------------

    @Override
    public void modificarNombre(Long id_adm, Long id_aut, String nombreNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(nombreNew == null){
            throw new CampoVacioException("El campo (nombre) a actualizar no contiene información.");
        }

        pAutorizada.setNombre(nombreNew);
        em.merge(pAutorizada);

    }

    // ---------------------------------------------------------------------

    public void testModifyNamePAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarINombre(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException){
            // ok -> all is ok
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    public void testModifyNamePAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException){
            // ok -> all is ok
        }
    }

    public void testModifyNamePAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException){
            // ok -> all is ok
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    public void testModifyIdentPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado

    }

    // ---------------------------------------------------------------------


    @Override
    public void modificarApellidos(Long id_adm, Long id_aut, String apellidosNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(apellidosNew == null){
            throw new CampoVacioException("El campo (apellidos) a actualizar no contiene información.");
        }

        pAutorizada.setApellidos(apellidosNew);
        em.merge(pAutorizada);

    }

    // ---------------------------------------------------------------------

    public void testModifySurNamePAutorizadaAdminNoExist(){
        final String NOMBRE = "mar";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarINombre(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion PersonaNoExisteException");
        } catch (PersonaNoExisteException){
            // ok -> all is ok
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y se esperaba PersonaNoExisteException");
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba PersonaNoExisteException");
        }
    }

    public void testModifyNamePAutorizadaNoEsAdministrador(){
        final String NOMBRE = "MA3S";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), "pau");
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion NoEsAdministradorException");
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y se esperaba NoEsAdministradorException");
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y se esperaba NoEsAdministradorException");
        } catch (NoEsAdministrativoException){
            // ok -> all is ok
        }
    }

    public void testModifyNamePAutorizadaCampoVacio(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            fail("Debe Lanzar una excepcion CampoVacioException");
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y se esperaba CampoVacioException");
        } catch (CampoVacioException){
            // ok -> all is ok
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y se esperaba CampoVacioException");
        }
    }

    public void testModifyIdentPAutorizadaValido(){
        final String NOMBRE = "Salva";

        Usuario user = new Usuario();
        user.setUser(NOMBRE);

        PAutorizada pAut = new PAutorizada();
        pAut.setId(parseLong("1", 1));

        try {
            gestionModificarPAutorizada.modificarIdentificacion(user.getUser(), pAut.getId(), null);
            // Si no devuelve nada
            // ok -> all is ok
        } catch (PersonaNoExisteException){
            fail("Se ha capturado PersonaNoExisteException y no se esperaba nada");
        } catch (CampoVacioException){
            fail("Se ha capturado CampoVacioException y no se esperaba nada");
        } catch (NoEsAdministrativoException){
            fail("Se ha capturado NoEsAdministrativoException y no se esperaba nada");
        }

        // Faltaría comprobar que se ha modificado

    }

    // ---------------------------------------------------------------------

    @Override
    public void modificarDireccion(Long id_adm, Long id_aut, String direccionNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(direccionNew == null){
            throw new CampoVacioException("El campo (dirección) a actualizar no contiene información.");
        }

        pAutorizada.setIdentificacion(direccionNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarFechaNacimiento(Long id_adm, Long id_aut, Date fechaNacimientoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(fechaNacimientoNew == null){
            throw new CampoVacioException("El campo (fecha de nacimiento) a actualizar no contiene información.");
        }

        pAutorizada.setFechaNacimiento(fechaNacimientoNew);
        em.merge(pAutorizada);
    }

    @Override
    public void modificarFechaInicio(Long id_adm, Long id_aut, Date fechaInicioNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(fechaInicioNew == null){
            throw new CampoVacioException("El campo (fecha de inicio) a actualizar no contiene información.");
        }

        pAutorizada.setFechaInicio(fechaInicioNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarFechaFin(Long id_adm, Long id_aut, Date fechaFinNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(fechaFinNew == null){
            throw new CampoVacioException("El campo (fecha de fin) a actualizar no contiene información.");
        }

        pAutorizada.setFechaFin(fechaFinNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarEstado(Long id_adm, Long id_aut, String estadoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(estadoNew == null){
            throw new CampoVacioException("El campo (estado) a actualizar no contiene información.");
        }

        pAutorizada.setEstado(estadoNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarTipo(Long id_adm, Long id_aut, String tipoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if(administrativo == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(pAutorizada == null) throw new PersonaNoExisteException("No existe nadie con id: " + id_adm + ".");

        if(tipoNew == null){
            throw new CampoVacioException("El campo (tipo) a actualizar no contiene información.");
        }

        pAutorizada.setTipo(tipoNew);
        em.merge(pAutorizada);

    }

}
