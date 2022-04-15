package ma3s.fintech;

import ma3s.fintech.excepciones.CampoVacioException;
import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

public class ModificarPAutorizada implements GestionModificarPAutorizada{

    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public void modificarIdentificacion(Long id_adm, Long id_aut, String identNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(identNew == null){
            throw new CampoVacioException("El campo (identificación) a actualizar no contiene información.");
        }

        pAutorizada.setIdentificacion(identNew);
        em.merge(pAutorizada);
    }

    @Override
    public void modificarNombre(Long id_adm, Long id_aut, String nombreNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(nombreNew == null){
            throw new CampoVacioException("El campo (nombre) a actualizar no contiene información.");
        }

        pAutorizada.setNombre(nombreNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarApellidos(Long id_adm, Long id_aut, String apellidosNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(apellidosNew == null){
            throw new CampoVacioException("El campo (apellidos) a actualizar no contiene información.");
        }

        pAutorizada.setApellidos(apellidosNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarDireccion(Long id_adm, Long id_aut, String direccionNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(direccionNew == null){
            throw new CampoVacioException("El campo (dirección) a actualizar no contiene información.");
        }

        pAutorizada.setIdentificacion(direccionNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarFechaNacimiento(Long id_adm, Long id_aut, Date fechaNacimientoNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(fechaNacimientoNew == null){
            throw new CampoVacioException("El campo (fecha de nacimiento) a actualizar no contiene información.");
        }

        pAutorizada.setFechaNacimiento(fechaNacimientoNew);
        em.merge(pAutorizada);


    }

    @Override
    public void modificarFechaInicio(Long id_adm, Long id_aut, Date fechaInicioNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(fechaInicioNew == null){
            throw new CampoVacioException("El campo (fecha de inicio) a actualizar no contiene información.");
        }

        pAutorizada.setFechaInicio(fechaInicioNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarFechaFin(Long id_adm, Long id_aut, Date fechaFinNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(fechaFinNew == null){
            throw new CampoVacioException("El campo (fecha de fin) a actualizar no contiene información.");
        }

        pAutorizada.setFechaFin(fechaFinNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarEstado(Long id_adm, Long id_aut, String estadoNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(estadoNew == null){
            throw new CampoVacioException("El campo (estado) a actualizar no contiene información.");
        }

        pAutorizada.setEstado(estadoNew);
        em.merge(pAutorizada);

    }

    @Override
    public void modificarTipo(Long id_adm, Long id_aut, String tipoNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException {
        Usuario administrativo = em.find(Usuario.class, id_adm);

        if (administrativo.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario " + id_adm + " no es administrativo.");
        }

        PAutorizada pAutorizada = em.find(PAutorizada.class, id_aut);

        /* Comprobar que la PAutorizada puede operar con cuentas de clientes que son personas jurídicas */
        /* Si no lo es lanzar excepción NoEsPAutorizadaException */

        if(tipoNew == null){
            throw new CampoVacioException("El campo (tipo) a actualizar no contiene información.");
        }

        pAutorizada.setTipo(tipoNew);
        em.merge(pAutorizada);

    }

    /*
    SE HA CREADO UN MÉTODO PARA CADA DATO QUE PODRIA MODIFICARSE DE UNA PAUTORIZADA
     */


}
