package ma3s.fintech;

import ma3s.fintech.excepciones.NoEsPAutorizadaException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

public class ModificarPAutorizada implements GestionModificarPAutorizada{

    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public void modificarIdentificacion(String identNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarNombre(String nombreNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarApellidos(String apellidosNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarDireccion(String direccionNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarFechaNacimiento(Date fechaNacimientoNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarFechaInicio(Date fechaInicioNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarFechaFin(Date fechaFinNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarEstado(String estadoNew) throws NoEsPAutorizadaException {

    }

    @Override
    public void modificarTipo(String tipoNew) throws NoEsPAutorizadaException {

    }
}
