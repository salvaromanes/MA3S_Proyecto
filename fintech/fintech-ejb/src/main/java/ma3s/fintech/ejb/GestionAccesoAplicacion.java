package ma3s.fintech.ejb;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.*;

public interface GestionAccesoAplicacion {
    void accederAplicacion(String usuario, String contrasena) throws AccesoException;
    Usuario entrarAplicacion(String usuario, String contrasena) throws AccesoException, CampoVacioException;
    Usuario entrarAplicacionAdministrador(String usuario, String contrasena) throws AccesoException, CampoVacioException;
    Usuario refrescarUsuario(Usuario u) throws CampoVacioException, AccesoException;
    Usuario refrescarUsuarioAdmin(Usuario u) throws CampoVacioException, AccesoException;
}
