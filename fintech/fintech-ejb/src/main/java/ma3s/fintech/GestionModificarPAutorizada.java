package ma3s.fintech;


import ma3s.fintech.excepciones.NoEsPAutorizadaException;

import java.util.Date;

public interface GestionModificarPAutorizada {

    public void modificarIdentificacion(Long id, String identNew) throws NoEsPAutorizadaException;

    public void modificarNombre(Long id, String nombreNew) throws NoEsPAutorizadaException;

    public void modificarApellidos(Long id, String apellidosNew) throws NoEsPAutorizadaException;

    public void modificarDireccion(Long id, String direccionNew) throws NoEsPAutorizadaException;

    public void modificarFechaNacimiento(Long id, Date fechaNacimientoNew) throws NoEsPAutorizadaException;

    public void modificarFechaInicio(Long id, Date fechaInicioNew) throws NoEsPAutorizadaException;

    public void modificarFechaFin(Long id, Date fechaFinNew) throws NoEsPAutorizadaException;

    public void modificarEstado(Long id, String estadoNew) throws NoEsPAutorizadaException;

    public void modificarTipo(Long id, String tipoNew) throws NoEsPAutorizadaException;
}
