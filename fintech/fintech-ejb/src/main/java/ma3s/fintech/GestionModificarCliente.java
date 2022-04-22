package ma3s.fintech;

import ma3s.fintech.excepciones.*;

public interface GestionModificarCliente {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, NoEsAdministrativoException;
    public void modIdenEmpresa(Long id, String iden) throws CampoVacioException, EmpresaNoExistenteException;
    public void modTipoEmpresa(Long id, String tipo_cliente) throws CampoVacioException, EmpresaNoExistenteException;
    public void modDireccionEmpresa(Long id, String direccion) throws CampoVacioException, EmpresaNoExistenteException;
    public void modCodigoPostalEmpresa(Long id, String codigoPostal) throws CampoVacioException, EmpresaNoExistenteException;
    public void modPaisEmpresa(Long id, String pais) throws CampoVacioException, EmpresaNoExistenteException;
    public void modRazonSocialEmpresa(Long id, String razon_social) throws CampoVacioException, EmpresaNoExistenteException;
    public void modIdenIndividual(Long id, String iden) throws CampoVacioException, IndividualNoExistenteException;
    public void modTipoClienteIndividual(Long id, String tipo_cliente) throws CampoVacioException, IndividualNoExistenteException;
    public void modDireccionIndividual(Long id, String direccion) throws CampoVacioException, IndividualNoExistenteException;
    public void modCodigoPostalIndividual(Long id, String codigoPostal) throws CampoVacioException, IndividualNoExistenteException;
    public void modPaisIndividual(Long id, String pais) throws CampoVacioException, IndividualNoExistenteException;
    public void modNombreIndividual(Long id, String nombre) throws CampoVacioException, IndividualNoExistenteException;
    public void modApellidoIndividual(Long id, String apellido) throws CampoVacioException, IndividualNoExistenteException;
}
