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
    public void accederAplicacion(String usuario, String contrasena) throws AccesoException, CuentaNoExistenteException, NoEsPAutorizadaException {
        Usuario user = em.find(Usuario.class, usuario);
        Cliente cliente = em.find(Cliente.class, user.getCliente().getId());
        PAutorizada pA = em.find(PAutorizada.class, user.getAutorizada().getId());
        List<Fintech> cuentas;

        if(user == null){
            throw new UsuarioIncorrectoException();
        }

        // el cliente no existe
        if(cliente == null) {
            throw new AccesoException("el cliente no existe, no puede acceder");
        // el cliente si existe, pero la PA no
        }

        else if(cliente != null && pA == null){
            cuentas = cliente.getCuentasFintech();

            if(cuentas.size()==0){
                throw new CuentaNoExistenteException();
            }
        }

        // el PA no existe
        if(pA == null) {
            throw new AccesoException("la persona autorizada no existe, no puede acceder");
        // el PA si existe, pero el cliente no
        }else if(pA != null && cliente == null){
            Autorizacion autorizacion = em.find(Autorizacion.class, user.getpAutorizada().getId());
            Empresa empresa = em.find(Empresa.class, autorizacion.getAutorizadaId());

            // personas autorizadas que tiene la empresa, estoy buscando por "autorizada"
            List<Autorizacion> autorizaciones = empresa.getAutorizaciones();
            if(autorizaciones.size()==0){
                throw new NoEsPAutorizadaException("la empresa no tiene autorizada a la persona con id " + autorizacion.getAutorizadaId());
            }

            // si la tiene autorizada, vemos en qué cuentas
            cuentas = empresa.getCuentasFintech();

            if(cuentas.size()==0){
                throw new CuentaNoExistenteException();
            }
        }
    }
}
