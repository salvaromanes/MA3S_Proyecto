package ma3s.fintech;

import ma3s.fintech.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BajaCliente implements GestionBajaCliente{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    @Override
    public void comprobarAdministrador(String usuario) throws UsuarioNoEncontradoException, NoEsAdministrativoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if(!user.getEsAdmin()){
            throw new NoEsAdministrativoException();
        }
    }

    @Override
    public void darBajaCliente(Long id) throws CampoVacioException, CuentaAbiertaException, ClienteNoExisteException {
        Cliente cliente = em.find(Cliente.class, id);

        if(id == null)
            throw new CampoVacioException("No se ha introducido un id para el cliente");

        if (cliente == null){
            throw new ClienteNoExisteException("Cliente no encontrado para el id: " + id);
        }

        List<Fintech> cuentas = cliente.getCuentasFintech();
        for (Fintech f : cuentas){
            if(f.getEstado().equals("Abierta"))
                throw new CuentaAbiertaException("No se puede dar de baja un cliente que tiene una cuenta abierta");
        }
        cliente.setEstado("Cerrada");
        em.merge(cliente);
    }
}
