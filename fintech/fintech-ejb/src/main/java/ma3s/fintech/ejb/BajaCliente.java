package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Fintech;
import ma3s.fintech.ejb.excepciones.*;
import org.primefaces.PrimeFaces;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
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
        if(id == null)
            throw new CampoVacioException("No se ha introducido un id para el cliente");

        Cliente cliente = em.find(Cliente.class, id);

        if (cliente == null){
            throw new ClienteNoExisteException("Cliente no encontrado para el id: " + id);
        }

        List<Fintech> cuentas = cliente.getCuentasFintech();
        for (Fintech f : cuentas){
            if(f.getEstado().equals("Activa"))
                throw new CuentaAbiertaException("No se puede dar de baja un cliente que tiene una cuenta abierta");
        }
        cliente.setEstado("Cerrada");
        cliente.setFechaBaja(new Date());
        em.merge(cliente);
    }

    public void showMessage(String msg) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", msg);

        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
}
