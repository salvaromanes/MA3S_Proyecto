package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.ejb.excepciones.*;

import java.util.Date;

public interface GestionModificarCliente {
    //Modificar Empresa
    public void modDireccionEmpresa(Long id, String direccion) throws CampoVacioException, EmpresaNoExistenteException;
    public void modCiudadEmpresa(Long id, String ciudad) throws CampoVacioException, EmpresaNoExistenteException;
    public void modCodigoPostalEmpresa(Long id, String codigoPostal) throws CampoVacioException, EmpresaNoExistenteException;
    public void modPaisEmpresa(Long id, String pais) throws CampoVacioException, EmpresaNoExistenteException;
    public void modRazonSocialEmpresa(Long id, String razon_social) throws CampoVacioException, EmpresaNoExistenteException;
    //Modificar Individual
    public void modDireccionIndividual(Long id, String direccion) throws CampoVacioException, IndividualNoExistenteException;
    public void modCiudadIndividual(Long id, String ciudad) throws CampoVacioException, IndividualNoExistenteException;
    public void modCodigoPostalIndividual(Long id, String codigoPostal) throws CampoVacioException, IndividualNoExistenteException;
    public void modPaisIndividual(Long id, String pais) throws CampoVacioException, IndividualNoExistenteException;
    public void modNombreIndividual(Long id, String nombre) throws CampoVacioException, IndividualNoExistenteException;
    public void modApellidoIndividual(Long id, String apellido) throws CampoVacioException, IndividualNoExistenteException;
    public void modFechaNacimientoIndividual(Long id, Date fecha_nacimiento) throws CampoVacioException, IndividualNoExistenteException;
}