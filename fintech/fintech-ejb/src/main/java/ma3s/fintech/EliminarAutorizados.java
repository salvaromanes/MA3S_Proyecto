package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

public class EliminarAutorizados implements GestionEliminarAutorizados{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    @Override
    public void darBaja(String usuario, Long idPA) throws PersonaNoExisteException {
        PAutorizada personaAutorizada = em.find(PAutorizada.class, idPA);
        Usuario administrativo = em.find(Usuario.class, usuario);
        Autorizacion autorizacion = em.find(Autorizacion.class, idPA);

        if(!administrativo.getUser().equals(usuario)){
            throw new PersonaNoExisteException("El administrativo " + administrativo + " no existe");
        }

        if(!personaAutorizada.getId().equals(idPA)){
            throw new PersonaNoExisteException("La persona autorizada con id " + idPA + " no existe");
        }

        if(isAdministrativo(administrativo.getUser()) &&
                isPersonaAutorizada(idPA, autorizacion.getEmpresaId().getId())){
            // esta sentencia está mal pero no sabría como dar la orden al administrativo
            // para que cambie el estado de la persona autorizada
            personaAutorizada.setEstado("Baja");
            personaAutorizada.setFechaFin(new Date()); // completa la fecha finc con el dia de hoy
        }
    }

    // ¿el usuario es administrativo? sirve para poder gestionar las bajas
    @Override
    public boolean isAdministrativo(String usuario) throws PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);
        if(!user.getUser().equals(usuario)){
            throw new PersonaNoExisteException("El usuario " + usuario + " no existe");
        }
        return user.getEsAdmin();
    }

    // ¿es el cliente una persona juridica?
    @Override
    public boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException {
        Cliente cliente = em.find(Cliente.class, tipoCliente);
        if(!cliente.getId().equals(id)) {
            throw new PersonaNoExisteException("El cliente con id " + id + " no existe");
        }
        return cliente.getTipoCliente().equals("Empresa");
    }

    // ¿la persona autorizada opera con personas juridicas?
    // la autorizacion debe ser de idPA y idCliente para que dicha persona opere con un cliente
    // el cual luego se comprobará si es persona autorizada
    @Override
    public boolean isPersonaAutorizada(Long idPA, Long idCliente) throws PersonaNoExisteException {
        PAutorizada personaAutorizada = em.find(PAutorizada.class, idPA);
        Cliente cliente = em.find(Cliente.class, idCliente);
        Autorizacion autorizacion = em.find(Autorizacion.class, idPA);

        if(!personaAutorizada.getId().equals(idPA)){
            throw new PersonaNoExisteException("La persona autorizada con id " + idPA + " no existe");
        }else if(!cliente.getId().equals(idCliente)) {
            throw new PersonaNoExisteException("El cliente con id " + idCliente + " no existe");
        }

        return autorizacion.getAutorizadaId().equals(personaAutorizada) &&
                autorizacion.getEmpresaId().equals(cliente) &&
                isClientePersonaJuridica(cliente.getId(), "Empresa");
    }
}
