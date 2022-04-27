package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Stateless

public class AccesoAplicacion implements GestionAccesoAplicacion {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public List<Fintech> accederAplicacion(String usuario, String contrasena) throws AccesoException, PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioIncorrectoException();
        }

        if(!contrasena.equals(user.getContrasena())){
            throw new ContraseñaIncorrectaException();
        }

        if(user.getCliente() == null){
            throw new PersonaNoExisteException("el cliente no existe");
        }
        Cliente cliente = em.find(Cliente.class, user.getCliente().getId());

        if(user.getpAutorizada() == null){
            throw new PersonaNoExisteException("la persona autorizada no existe");
        }
        PAutorizada pA = em.find(PAutorizada.class, user.getAutorizada().getId());

        List<Fintech> cuentas = null;

        if (cliente != null){
            cuentas = cliente.getCuentasFintech();
        }else if(pA != null){
            Autorizacion autorizacion = em.find(Autorizacion.class, pA.getId());
            Empresa empresa = em.find(Empresa.class, autorizacion.getAutorizadaId());
            cuentas = empresa.getCuentasFintech();
        }
        return cuentas;
    }
}
