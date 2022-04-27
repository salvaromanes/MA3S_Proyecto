package ma3s.fintech.ejb;

import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.NoEsAdministrativoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

public interface GestionAltaCliente {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, NoEsAdministrativoException;
    public void darAltaEmpresa(Empresa empresa) throws CampoVacioException;
    public void darAltaIndividual(Individual individual) throws CampoVacioException;
}
