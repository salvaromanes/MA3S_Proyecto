package ma3s.fintech;


import ma3s.fintech.excepciones.CampoVacioException;
import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;

import java.util.Date;

public interface GestionModificarPAutorizada {

    public void modificarIdentificacion(Long id_adm, Long id_aut, String identNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarNombre(Long id_adm, Long id_aut, String nombreNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarApellidos(Long id_adm, Long id_aut, String apellidosNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarDireccion(Long id_adm, Long id_aut, String direccionNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarFechaNacimiento(Long id_adm, Long id_aut, Date fechaNacimientoNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarFechaInicio(Long id_adm, Long id_aut, Date fechaInicioNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarFechaFin(Long id_adm, Long id_aut, Date fechaFinNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarEstado(Long id_adm, Long id_aut, String estadoNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;

    public void modificarTipo(Long id_adm, Long id_aut, String tipoNew) throws CampoVacioException, NoEsAdministrativoException, NoEsPAutorizadaException;
}
