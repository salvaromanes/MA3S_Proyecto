package ma3s.fintech.ejb;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Fintech;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class GetCuentasUnicoCliente implements GestionGetCuentasUnCliente{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public List<Fintech> getCuentas(String ident){
        TypedQuery<Fintech> query = em.createQuery("select c from Fintech c where c.cliente.identificacion like :cliente", Fintech.class);
        query.setParameter("cliente",ident);
        List<Fintech> listacuentacliente = query.getResultList();
        return listacuentacliente;
    }
}
