package ma3s.fintech.ejb;


import ma3s.fintech.ejb.excepciones.*;

import java.util.Date;

public interface GestionModificarPAutorizada {

    public void modificarNombre(String id_adm, Long id_aut, String nombreNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarApellidos(String id_adm, Long id_aut, String apellidosNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarDireccion(String id_adm, Long id_aut, String direccionNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarFechaNacimiento(String id_adm, Long id_aut, Date fechaNacimientoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarFechaInicio(String id_adm, Long id_aut, Date fechaInicioNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarFechaFin(String id_adm, Long id_aut, Date fechaFinNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException;

    public void modificarEstado(String id_adm, Long id_aut, String estadoNew) throws PersonaNoExisteException, CampoVacioException, NoEsAdministrativoException, EstadoNoValidoException;
}
