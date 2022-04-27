package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteYaExistenteException;

public interface GestionAltaCliente {
    public void darAltaEmpresa(Empresa empresa) throws CampoVacioException, ClienteYaExistenteException;
    public void darAltaIndividual(Individual individual) throws CampoVacioException, ClienteYaExistenteException;
}
