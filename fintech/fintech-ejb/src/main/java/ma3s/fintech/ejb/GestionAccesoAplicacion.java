package ma3s.fintech.ejb;

import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.*;

public interface GestionAccesoAplicacion {
    void accederAplicacion(String usuario, String contrasena) throws AccesoException;
    public Usuario entrarAplicacion(String usuario, String contrasena) throws AccesoException, ErrorInternoException;
}
