package ma3s.fintech.ejb;

import ma3s.fintech.Fintech;
import ma3s.fintech.ejb.excepciones.*;

import java.util.List;

public interface GestionAccesoAplicacion {

    List<Fintech> accederAplicacion(String usuario, String contrasena) throws AccesoException, PersonaNoExisteException;
}
