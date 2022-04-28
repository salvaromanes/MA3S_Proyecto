package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AccesoAplicacion implements GestionAccesoAplicacion {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void accederAplicacion(String usuario, String contrasena) throws AccesoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioIncorrectoException();
        }

        if(!contrasena.equals(user.getContrasena())){
            throw new ContraseñaIncorrectaException();
        }
/*
        List<Fintech> cuentas = null;

        if(user.getCliente() == null){
            throw new PersonaNoExisteException("el cliente no existe");
        }else{
            Cliente cliente = em.find(Cliente.class, user.getCliente().getId());
            if(cliente == null){
                throw new PersonaNoExisteException("el cliente no existe");
            }
            cuentas = cliente.getCuentasFintech();
        }

        if(user.getpAutorizada() == null){
            throw new PersonaNoExisteException("la persona autorizada no existe");
        }else{
            PAutorizada pA = em.find(PAutorizada.class, user.getAutorizada().getId());
            Autorizacion autorizacion = em.find(Autorizacion.class, pA.getId());
            Empresa empresa = em.find(Empresa.class, autorizacion.getAutorizadaId());
            if(pA == null){
                throw new PersonaNoExisteException("la persona autorizada no existe");
            }
            cuentas = empresa.getCuentasFintech();
        }

        return cuentas;

 */
    }
}
