package ma3s.fintech.ejb;

import ma3s.fintech.Fintech;
import ma3s.fintech.ejb.excepciones.*;

import java.util.List;

public interface GestionAccesoAplicacion {

    void accederAplicacion(String usuario, String contrasena) throws AccesoException;
}
