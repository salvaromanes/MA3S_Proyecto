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
    public List<Fintech> accederAplicacion(String usuario, String contrasena) throws AccesoException{
        Usuario user = em.find(Usuario.class, usuario);
        Cliente cliente = em.find(Cliente.class, user.getCliente().getId());
        PAutorizada pA = em.find(PAutorizada.class, user.getAutorizada().getId());
        List<Fintech> cuentas = null;

        if(user == null){
            throw new UsuarioIncorrectoException();
        }

        if(!contrasena.equals(user.getContrasena())){
            throw new ContraseñaIncorrectaException();
        }

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
