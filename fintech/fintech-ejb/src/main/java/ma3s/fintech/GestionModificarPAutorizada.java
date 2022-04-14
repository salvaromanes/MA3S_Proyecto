package ma3s.fintech;


import ma3s.fintech.excepciones.NoEsPAutorizadaException;

import java.util.Date;

public interface GestionModificarPAutorizada {

    public void modificarIdentificacion(String identNew) throws NoEsPAutorizadaException;

    public void modificarNombre(String nombreNew) throws NoEsPAutorizadaException;

    public void modificarApellidos(String apellidosNew) throws NoEsPAutorizadaException;

    public void modificarDireccion(String direccionNew) throws NoEsPAutorizadaException;

    public void modificarFechaNacimiento(Date fechaNacimientoNew) throws NoEsPAutorizadaException;

    public void modificarFechaInicio(Date fechaInicioNew) throws NoEsPAutorizadaException;

    public void modificarFechaFin(Date fechaFinNew) throws NoEsPAutorizadaException;

    public void modificarEstado(String estadoNew) throws NoEsPAutorizadaException;

    public void modificarTipo(String tipoNew) throws NoEsPAutorizadaException;
}
