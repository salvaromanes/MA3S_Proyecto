package ma3s.fintech;

import ma3s.fintech.excepciones.CampoVacioException;
import ma3s.fintech.excepciones.NoEsAdministrativoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

public interface GestionAltaCliente {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, NoEsAdministrativoException;
    public void darAltaEmpresa(Empresa empresa) throws CampoVacioException;
    public void darAltaIndividual(Individual individual) throws CampoVacioException;
}
