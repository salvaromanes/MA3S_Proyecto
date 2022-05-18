package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.UsuarioExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OperacionUsuario implements GestionOperacionUsuario {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void crearUsuario(Usuario usuario) throws UsuarioExistenteException {
        Usuario user = em.find(Usuario.class, usuario.getUser());

        if(user != null){
            throw new UsuarioExistenteException("El usuario "+user.getUser()+" ya existe");
        }

        em.persist(usuario);
    }

    @Override
    public void cambiarContraseña(String contrasena, String usuario) throws UsuarioNoEncontradoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException("El usuario "+usuario+" no ha sido encontrado");
        }

        user.setContrasena(contrasena);

        em.merge(user);
    }

    @Override
    public void cambiarEstado(String estado, String usuario) throws UsuarioNoEncontradoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException("El usuario "+usuario+" no ha sido encontrado");
        }

        user.setEstado(estado);

        em.merge(user);
    }

    @Override
    public void asignarAutorizada(PAutorizada pAutorizada, String usuario) throws UsuarioNoEncontradoException, PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException("El usuario "+usuario+" no ha sido encontrado");
        }

        if(pAutorizada == null){
            throw new PersonaNoExisteException("La persona autorizada "+pAutorizada.getNombre()+" no ha sido encontrada");
        }

        user.setpAutorizada(pAutorizada);

        em.merge(user);
    }

    @Override
    public void asignarCliente(Individual individual, String usuario) throws UsuarioNoEncontradoException, PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException("El usuario "+usuario+" no ha sido encontrado");
        }

        if(individual == null){
            throw new PersonaNoExisteException("El cliente "+individual.getIdentificacion()+" no ha sido encontrado");
        }

        user.setCliente(individual);

        em.merge(user);
    }

    @Override
    public void bloquearUsuario(Long id) throws UsuarioNoEncontradoException {
        Usuario usuario = em.find(Usuario.class, id);

        if(usuario == null){
            throw new UsuarioNoEncontradoException("El usuario con id "+id+" no ha sido encontrado");
        }

        usuario.setEstado("Bloqueado");

        em.merge(usuario);
    }
}
