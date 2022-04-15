package ma3s.fintech;

import ma3s.fintech.excepciones.ContraseñaIncorrectaException;
import ma3s.fintech.excepciones.PersonaNoExisteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AccesoAplicacion implements GestionAccesoAplicacion {
    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public void accederAplicacion(Long idCliente, String usuario, String contrasena) throws PersonaNoExisteException, UsuarioIncorrectoException, ContraseñaIncorrectaException {
        Cliente cliente = em.find(Cliente.class, idCliente);
        Autorizacion autorizacion = em.find(Autorizacion.class, idCliente);
        Usuario user = em.find(Usuario.class, usuario);
        Cuenta cuenta = em.find(Cuenta.class, cliente.getCuentasFintech());
        Transaccion transaccionOrigen = em.find(Transaccion.class, cuenta.getTransaccionesOrigen());
        Transaccion transaccionDestino = em.find(Transaccion.class, cuenta.getTransaccionesDestino());
        Divisa divisaOrigenEmisor = em.find(Divisa.class, transaccionOrigen.getDivisaEmisor());
        Divisa divisaOrigenReceptor = em.find(Divisa.class, transaccionOrigen.getDivisaReceptor());
        Divisa divisaDestinoEmisor = em.find(Divisa.class, transaccionDestino.getDivisaEmisor());
        Divisa divisaDestinoReceptor = em.find(Divisa.class, transaccionDestino.getDivisaReceptor());

        if(!cliente.getId().equals(idCliente)) {
            throw new PersonaNoExisteException("El cliente con id " + idCliente + " no existe");
        }

        // si el cliente no es una persona juridica, si puede acceder a la app
        if (!isClientePersonaJuridica(cliente.getId(), "Empresa") &&
            isPersonaAutorizada(cliente.getId(), autorizacion.getEmpresaId().getId()) &&
            user.getUser().equals(cliente.user.getUser())){
                if (!user.getUser().equals(usuario)){
                    throw new UsuarioIncorrectoException();
                }

                if(!user.getContrasena().equals(contrasena)){
                    throw new ContraseñaIncorrectaException();
                }

                // ¿como poner la siguiente info para que sea visible al usuario?
                // cuenta : lista de cuentas que tiene el cliente
                // transaccion : transaccionOrigen y Destino
                // divisa : los 4 tipos de divisa

                // FALTA AÑADIR REFERENCIA Y DEPOSITADA_EN (no sé como enlazarlo)


        }
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

    // ¿la persona autorizada: lleva cuentas de clientes juridicos o es persona fisica?
    // cliente juridico: Empresa
    // persona fisica: Individual
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
                isClientePersonaJuridica(cliente.getId(), "Empresa") &&
                isClientePersonaJuridica(cliente.getId(), "Individual");
    }
}
