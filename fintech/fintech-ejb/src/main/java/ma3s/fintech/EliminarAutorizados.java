package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExiste;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EliminarAutorizados implements GestionEliminarAutorizados{
    @PersistenceContext(name="fintech") // NO SE QUÉ HABRIA QUE PONER AQUI EN VEZ DE FINTECH
    private EntityManager em;


    /*
    * - crear un administrativo que de las bajas a las personas autorizadas
    * - si la persona autorizada no existe lanza excepcion
    * - si la persona autorizada existe se le da la baja, pero hay que
    *   comprobar que dicha persona opere con cuentas de clientes de tipo juridico
    */

    @Override
    public void darBaja(Long idAdministrativo, Long idPA) throws PersonaNoExiste {
        PAutorizada personaAutorizada = em.find(PAutorizada.class, idPA);
        PAutorizada administrativo = em.find(PAutorizada.class, idAdministrativo);

        if(personaAutorizada.getId() != idPA){
            throw new PersonaNoExiste("La persona buscada con id " + idPA + " no existe");
        }

        personaAutorizada.setEstado("Baja");
    }


    // id - persona autorizada
    // tipoCliente - comprobar si el cliente es una persona juridica
    // comprobar si la persona autorizada trabaja con clientes que sean persona juridica

    /*
    * Se comprueba que ess persona juridica para añadirlo en la coleccion del metodo superior
    */

    @Override
    public boolean isPersonaJuridica(Long id, String tipoCliente) throws PersonaNoExiste {
        Cliente cliente = em.find(Cliente.class, tipoCliente);
        PAutorizada personaAutorizada = em.find(PAutorizada.class, id);
        Autorizacion autorizacion = em.find(Autorizacion.class, id);
        //if (autorizacion.getAutorizadaId() == personaAutorizada && autorizacion.getTipo())
        return false;
    }

}
