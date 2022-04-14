package ma3s.fintech;

import ma3s.fintech.excepciones.CampoVacioException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

public interface GestionAltaCliente {
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException;
    public void darAltaEmpresa(Long id, String identificacion, String tipo_cliente, String direccion, String ciudad, String codigoPostal, String pais, String razon_social) throws CampoVacioException;
    public void darAltaIndividual(Long id, String identificacion, String tipo_cliente, String direccion, String ciudad, String codigoPostal, String pais, String nombre, String apellido) throws CampoVacioException;
}
