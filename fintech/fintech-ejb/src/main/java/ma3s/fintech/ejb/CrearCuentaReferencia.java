package ma3s.fintech.ejb;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Referencia;
import ma3s.fintech.ejb.excepciones.CuentaReferenciaYaExisteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CrearCuentaReferencia implements GestionCrearCuentaReferencia{

    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void anyadirCuentaReferencia(Referencia referencia) throws CuentaReferenciaYaExisteException {

        Referencia ref = em.find(Referencia.class, referencia.getIban());

        if(ref != null){
            throw new CuentaReferenciaYaExisteException("La cuenta ya existe en la base de datos");
        }

        em.persist(referencia);

    }

}
