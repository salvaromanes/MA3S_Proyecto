package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.UsuarioExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

public interface GestionOperacionUsuario {
    public void crearUsuario(Usuario usuario) throws UsuarioExistenteException;
    public void cambiarContraseña(String contrasena, String usuario) throws UsuarioNoEncontradoException;
    public void cambiarEstado(String estado, String usuario) throws UsuarioNoEncontradoException;
    public void asignarAutorizada(PAutorizada pAutorizada, String usuario) throws UsuarioNoEncontradoException, PersonaNoExisteException;
    public void asignarCliente(Individual individual, String usuario) throws UsuarioNoEncontradoException, PersonaNoExisteException;
    public void bloquearUsuario(Long id) throws UsuarioNoEncontradoException;
}
