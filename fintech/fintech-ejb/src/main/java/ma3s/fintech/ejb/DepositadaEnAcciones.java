package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Referencia;
import ma3s.fintech.DepositadaEn;
import ma3s.fintech.ejb.excepciones.PooledException;
import ma3s.fintech.ejb.excepciones.ReferenciaException;
import ma3s.fintech.ejb.excepciones.SaldoNoSuficiente;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;



@Stateless
public class DepositadaEnAcciones implements GestionDepositadaEn {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void crearDeposito(Pooled pooled, double saldo, Referencia referencia) throws PooledException, SaldoNoSuficiente, ReferenciaException {

        System.out.println("REFERENCIA2:\n" + referencia.toString());
        System.out.println("POOLED2:\n" + pooled.toString());

//        if(pooled.getIban() == null) throw new PooledException("El parametro iban esta nulo");
//
//        Pooled pooled1 = em.find(Pooled.class, pooled.getIban());
//        Referencia referencia1 = em.find(Referencia.class, referencia.getIban());
//
//        if(!pooled1.equals(pooled) || pooled1 == null){
//            throw new PooledException("La cuenta: " + pooled + " no existe");
//        }
//
//        if(saldo < 0){
//            throw new SaldoNoSuficiente("Saldo errorneo");
//        }
//
//        if(!referencia1.equals(referencia) || referencia1 == null){
//            throw new ReferenciaException("La referencia no existe");
//        }
//
//        ma3s.fintech.DepositadaEn depositadaEn = new ma3s.fintech.DepositadaEn();
//        depositadaEn.setIbanPooled(pooled);
//        depositadaEn.setReferencia(referencia);
//        depositadaEn.setSaldo(saldo);
//        em.persist(depositadaEn);

        Query query = em.createQuery("Select c from Pooled c");
        List<Pooled> pooleds = new ArrayList<>();
        Pooled pooledA = new Pooled();

        if(pooleds != null){
            for(Pooled p : pooleds){
                if(p.getIban().equals(pooled.getIban())) pooledA = p;
            }
        }

        if(pooledA == null) throw new PooledException("La cuenta pooled no existe");

        query = em.createQuery("Select c from Referencia c");
        List<Referencia> referencias = new ArrayList<>();
        Referencia referenciaA = new Referencia();

        if(referencias != null){
            for(Referencia p : referencias){
                if(p.getIban().equals(referencia.getIban())) referenciaA = p;
            }
        }

        if(referenciaA == null) throw new ReferenciaException("La cuenta pooled no existe");

        if(saldo < 0){
            throw new SaldoNoSuficiente("Saldo errorneo");
        }

        ma3s.fintech.DepositadaEn depositadaEn = new ma3s.fintech.DepositadaEn();
        depositadaEn.setIbanPooled(pooled);
        depositadaEn.setReferencia(referencia);
        depositadaEn.setSaldo(saldo);
        em.persist(depositadaEn);


    }


    @Override
    public void actualizarDeposito(Pooled pooled, double saldo, Referencia referencia) throws PooledException, SaldoNoSuficiente, ReferenciaException {
        Pooled pooled1 = em.find(Pooled.class, pooled.getIban());
        Referencia referencia1 = em.find(Referencia.class, referencia.getIban());

        if(!pooled1.equals(pooled) || pooled1 == null){
            throw new PooledException("La cuenta: " + pooled + " no existe");
        }

        if(saldo < 0){
            throw new SaldoNoSuficiente("Saldo errorneo");
        }

        if(!referencia1.equals(referencia) || referencia1 == null){
            throw new ReferenciaException("La referencia no existe");
        }

        Query query = em.createQuery("select a from DepositadaEn a");
        List<DepositadaEn> depo_en = query.getResultList();

        int max = depo_en.size();
        int i = 0;
        boolean encontrado = false;
        while(i<max && !encontrado){
            DepositadaEn depo_aux = depo_en.get(i);
            if(depo_aux.getIbanPooled().equals(pooled) &&
                    depo_aux.getReferencia().equals(referencia)){
                encontrado = true;
            }else {
                i++;
            }
        }

        if(encontrado){
            DepositadaEn a = new DepositadaEn();
            a.setIbanPooled(pooled);
            a.setIbanReferencia(referencia);
            a.setSaldo(saldo);
            em.merge(a);
        }

    }
}