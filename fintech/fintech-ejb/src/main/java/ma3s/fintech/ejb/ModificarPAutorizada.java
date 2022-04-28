package ma3s.fintech.ejb;

import ma3s.fintech.PAutorizada;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class ModificarPAutorizada implements GestionModificarPAutorizada{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void modificarIdentificacion(String id_adm, Long id_aut, String identNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
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

    @Override
    public void modificarNombre(String id_adm, Long id_aut, String nombreNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
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


    @Override
    public void modificarApellidos(String id_adm, Long id_aut, String apellidosNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
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

    @Override
    public void modificarDireccion(String id_adm, Long id_aut, String direccionNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
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
    public void modificarFechaNacimiento(String id_adm, Long id_aut, Date fechaNacimientoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
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
    public void modificarFechaInicio(String id_adm, Long id_aut, Date fechaInicioNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
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
    public void modificarFechaFin(String id_adm, Long id_aut, Date fechaFinNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException {
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
    public void modificarEstado(String id_adm, Long id_aut, String estadoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException, EstadoNoValidoException {
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

        if(estadoNew != "activo" || estadoNew != "inactivo" || estadoNew != "bloqueado"){
            throw new EstadoNoValidoException("El estado " + estadoNew + " no es un estado válido.");
        }

        pAutorizada.setEstado(estadoNew);
        em.merge(pAutorizada);

    }

}
