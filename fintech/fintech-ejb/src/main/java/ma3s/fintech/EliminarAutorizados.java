package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EliminarAutorizados implements GestionEliminarAutorizados{
    @PersistenceContext(name="fintech") // NO SE QUÉ HABRIA QUE PONER AQUI EN VEZ DE FINTECH
    private EntityManager em;

    // supongo que el administrativo es lo mismo que una persona autorizada
    @Override
    public void darBaja(Long idAdministrativo, Long idPA) throws PersonaNoExisteException {
        PAutorizada personaAutorizada = em.find(PAutorizada.class, idPA);
        PAutorizada administrativo = em.find(PAutorizada.class, idAdministrativo);
        Autorizacion autorizacion = em.find(Autorizacion.class, idPA);

        if(!personaAutorizada.getId().equals(idPA)){
            throw new PersonaNoExisteException("La persona autorizada con id " + idPA + " no existe");
        }else if(!administrativo.getId().equals(idAdministrativo)){
            throw new PersonaNoExisteException("El administrativo con id " + idAdministrativo + " no existe");
        }

        if(isPersonaAutorizada(idPA, autorizacion.getEmpresaId().getId())){
            // esta sentencia está mal pero no sabría como dar la orden al administrativo
            // para que cambie el estado de la persona autorizada
            personaAutorizada.setEstado("Baja");
        }
    }

    // ¿Es el cliente una persona jurídica?
    @Override
    public boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException {
        Cliente cliente = em.find(Cliente.class, tipoCliente);
        if(!cliente.getId().equals(id)) {
            throw new PersonaNoExisteException("El cliente con id " + id + " no existe");
        }
        return cliente.getTipoCliente().equals("persona juridica");
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
                isClientePersonaJuridica(cliente.getId(), "persona juridica");
    }


}
