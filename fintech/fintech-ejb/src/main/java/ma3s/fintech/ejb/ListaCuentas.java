package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Transaccion;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ListaCuentas implements GestionListaCuenta{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;



    @Override
    public Transaccion getTransaccion(String iban) {
        return em.find(Transaccion.class, iban);
    }
}
