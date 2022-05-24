package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteYaExistenteException;
import ma3s.fintech.ejb.excepciones.PautorizadaYaExistenteException;

public interface GestionAltaPAutorizada {
    public void darAltaPA(PAutorizada pAutorizada) throws CampoVacioException, PautorizadaYaExistenteException;
}
