package ma3s.fintech.ejb;

import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import java.io.IOException;
import java.util.Date;

public interface GestionGenerarCSV {
    void generarCSV(String usuario, String tipoInforme, Date ultimoReporte) throws PersonaNoExisteException, IOException, UsuarioNoEncontradoException;
    String generarCSV(String usuario, String tipoInforme) throws PersonaNoExisteException, IOException, UsuarioNoEncontradoException;
}
