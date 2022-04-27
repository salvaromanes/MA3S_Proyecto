package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Fintech;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class BajaCliente implements GestionBajaCliente{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

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
        cliente.setFechaBaja(new Date());
        em.merge(cliente);
    }
}
