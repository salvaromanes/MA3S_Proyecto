package ma3s.fintech;

import ma3s.fintech.excepciones.NoEsPAutorizadaException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

public class ModificarPAutorizada implements GestionModificarPAutorizada{

    @PersistenceContext(name="fintech")
    private EntityManager em;

    /*
    SE HA CREADO UN MÉTODO PARA CADA DATO QUE PODRIA MODIFICARSE DE UNA PAUTORIZADA
     */


    @Override
    public void modificarIdentificacion(Long id, String identNew) throws NoEsPAutorizadaException {
        /* Comprobar que el id es de una PAutorizada y que esta tiene una autorización con alguna empresa */

        /* Modificar el valor a actualizar */

    }

    @Override
    public void modificarNombre(Long id, String nombreNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarApellidos(Long id, String apellidosNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarDireccion(Long id, String direccionNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarFechaNacimiento(Long id, Date fechaNacimientoNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarFechaInicio(Long id, Date fechaInicioNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarFechaFin(Long id, Date fechaFinNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarEstado(Long id, String estadoNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarTipo(Long id, String tipoNew) throws NoEsPAutorizadaException {

    }
}
