package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.AccesoException;
import ma3s.fintech.ejb.excepciones.CuentaExistenteException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Stateless

public class AccesoAplicacion implements GestionAccesoAplicacion {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void accederAplicacion(String usuario) throws PersonaNoExisteException, AccesoException, CuentaExistenteException {
        Usuario user = em.find(Usuario.class, usuario);
        Cliente cliente = em.find(Cliente.class, user.getCliente().getId());
        PAutorizada autorizada = em.find(PAutorizada.class, user.getAutorizada().getId());

        if(!user.getUser().equals(usuario)){
            throw new UsuarioIncorrectoException();
        }
        
        List<Fintech> cuentas;

        if (comprobarCliente(user, cliente)){
            cuentas = accederAplicacionCliente(user, cliente);
        }else if (comprobarPA(user, autorizada)){
            cuentas = accederAplicacionPAutorizada(user, autorizada);
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

    // ¿la persona autorizada: lleva cuentas de clientes juridicos?
    // cliente juridico: Empresa
    // persona fisica: Individual
    @Override
    public boolean isPersonaAutorizada(Long idPA, Long idCliente) throws PersonaNoExisteException {
        PAutorizada personaAutorizada = em.find(PAutorizada.class, idPA);
        Cliente cliente = em.find(Cliente.class, idCliente);
        Autorizacion autorizacion = em.find(Autorizacion.class, idPA);

        if(!personaAutorizada.getId().equals(idPA)){
            throw new PersonaNoExisteException("La persona autorizada con id " + idPA + " no existe");
        }

        if(!cliente.getId().equals(idCliente)) {
            throw new PersonaNoExisteException("El cliente con id " + idCliente + " no existe");
        }

        return autorizacion.getAutorizadaId().equals(personaAutorizada) &&
                autorizacion.getEmpresaId().equals(cliente) &&
                isClientePersonaJuridica(cliente.getId(), "Empresa");
    }

    // comprobar si el id usuario existe en Cliente y comprobar que sea persona Fisica = Individual
    private boolean comprobarCliente(Usuario user, Cliente cliente) throws PersonaNoExisteException, AccesoException {
        if(!user.getUser().equals(cliente.getUser().getUser())){
            throw new PersonaNoExisteException("el cliente con id " + cliente.getId() + " y usuario " + cliente.getUser() + " no existe");
        }

        if (!cliente.getTipoCliente().equals("Individual")){
            throw new AccesoException("El cliente " + cliente.getId() + " no es una persona fisica y no tiene acceso");
        }
        return true;
    }

    // comprobar si el id usuario existe en PAutorizada y si esta autorizado a cuentas de clientes juridicos = empresas
    private boolean comprobarPA(Usuario user, PAutorizada autorizada) throws PersonaNoExisteException, AccesoException {
        if(!user.getUser().equals(autorizada.getUser().getUser())){
            throw new PersonaNoExisteException("la persona autorizada con id " + autorizada.getId() + " y usuario " + autorizada.getUser() + " no existe");
        }

        if(!isPersonaAutorizada(autorizada.getId(), user.getCliente().getId())){
            throw new AccesoException("La persona autorizada " + autorizada.getId() + " no tiene acceso a cuentas de clientes juridicos");
        }
        return true;
    }

    // accedo a la aplicacion como Cliente y devuelto la lista de cuentas que tiene
    private List<Fintech> accederAplicacionCliente(Usuario user, Cliente cliente) throws CuentaExistenteException {
        List<Fintech> cuentas = cliente.getCuentasFintech();

        // compruebo si el cliente tiene cuentas asociadas
        if(cuentas.size()==0){
            throw new CuentaExistenteException();
        }

        return cuentas;
    }

    // accedo a la aplicacion como Persona Autorizada
    private List<Fintech> accederAplicacionPAutorizada(Usuario user, PAutorizada autorizada) throws PersonaNoExisteException, CuentaExistenteException {

        Autorizacion autorizacion = em.find(Autorizacion.class, user.getpAutorizada().getId());
        Empresa empresa = em.find(Empresa.class, autorizacion.getAutorizadaId());
        // personas autorizadas que tiene la empresa, estoy buscando por "autorizada"
        List<Autorizacion> personasAutorizadas = empresa.getAutorizaciones();
        if(personasAutorizadas.size()==0){
            throw new PersonaNoExisteException("la empresa no tiene autorizada a la persona con id " + autorizacion.getAutorizadaId());
        }

        // si la tiene autorizada, vemos en qué cuentas
        List<Fintech> cuentas = empresa.getCuentasFintech();

        if(cuentas.size()==0){
            throw new CuentaExistenteException();
        }

        return cuentas;
    }
}
