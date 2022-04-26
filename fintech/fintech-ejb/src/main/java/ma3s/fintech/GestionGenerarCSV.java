package ma3s.fintech;

import ma3s.fintech.excepciones.AccesoException;
import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.PersonaNoExisteException;

import java.io.IOException;
import java.util.Date;

public interface GestionGenerarCSV {
    void generarCSV(String usuario, String tipoInforme, Date ultimoReporte) throws PersonaNoExisteException, IOException;
}