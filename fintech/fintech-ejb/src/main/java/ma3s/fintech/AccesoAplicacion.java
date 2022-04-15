package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccesoAplicacion implements GestionAccesoAplicacion {
    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public void accederAplicacion(Long idCliente) throws PersonaNoExisteException {
        Cliente cliente = em.find(Cliente.class, idCliente);
        Autorizacion autorizacion = em.find(Autorizacion.class, idCliente);

        if(!cliente.getId().equals(idCliente)) {
            throw new PersonaNoExisteException("El cliente con id " + idCliente + " no existe");
        }

        // si el cliente no es una persona juridica, si puede acceder a la app
        if (!isClientePersonaJuridica(cliente.getId(), "persona juridica") &&
            cliente.getId().equals(idCliente) &&
            isPersonaAutorizada(cliente.getId(), autorizacion.getEmpresaId().getId())){
                // ------------- NO HAY CON QUÉ PEDIR EL USUARIO Y CONTRASEÑA A UNA CUENTA
                // PONER: transacciones, cuentas a las que se tiene acceso, otra info
        }
    }

    // ¿es el cliente una persona juridica?
    @Override
    public boolean isClientePersonaJuridica(Long id, String tipoCliente) throws PersonaNoExisteException {
        Cliente cliente = em.find(Cliente.class, tipoCliente);
        if(!cliente.getId().equals(id)) {
            throw new PersonaNoExisteException("El cliente con id " + id + " no existe");
        }
        return cliente.getTipoCliente().equals("persona juridica");
    }

    // ¿la persona autorizada lleva cuentas de clientes juridicos?
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
