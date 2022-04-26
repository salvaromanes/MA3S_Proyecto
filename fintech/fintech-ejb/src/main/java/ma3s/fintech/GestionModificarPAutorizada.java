package ma3s.fintech;


import ma3s.fintech.excepciones.CampoVacioException;
import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.excepciones.PersonaNoExisteException;

import java.util.Date;

public interface GestionModificarPAutorizada {

    public void modificarIdentificacion(Long id_adm, Long id_aut, String identNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarNombre(Long id_adm, Long id_aut, String nombreNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarApellidos(Long id_adm, Long id_aut, String apellidosNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarDireccion(Long id_adm, Long id_aut, String direccionNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarFechaNacimiento(Long id_adm, Long id_aut, Date fechaNacimientoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarFechaInicio(Long id_adm, Long id_aut, Date fechaInicioNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarFechaFin(Long id_adm, Long id_aut, Date fechaFinNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarEstado(Long id_adm, Long id_aut, String estadoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarTipo(Long id_adm, Long id_aut, String tipoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;
}
