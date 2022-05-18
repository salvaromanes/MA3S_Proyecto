package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CrearDepositadaEn implements GestionDepositadaEn {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void crearDeposito(Pooled pooled, double saldo, Referencia referencia) throws PooledException, SaldoNoSuficiente, ReferenciaException {

        Pooled pooled1 = em.find(Pooled.class, pooled);
        Referencia referencia1 = em.find(Referencia.class, referencia);

        if(!pooled1.equals(pooled) || pooled1 == null){
            throw new PooledException("La cuenta: " + pooled + " no existe");
        }

        if(saldo < 0){
            throw new SaldoNoSuficiente("Saldo errorneo");
        }

        if(!referencia1.equals(referencia)){
            throw new ReferenciaException("La referencia no existe");
        }

        DepositadaEn depositadaEn = new DepositadaEn();
        depositadaEn.setIbanPooled(pooled.getIban());
        depositadaEn.setReferencia(referencia);
        depositadaEn.setSaldo(saldo);
        depositadaEn.setIbanReferencia(referencia.getIban());
        em.persist(depositadaEn);


    }


}
