package ma3s.fintech.ejb;

import ma3s.fintech.Pooled;
import ma3s.fintech.Referencia;
import ma3s.fintech.Segregada;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CrearDepositadaEn implements GestionDepositadaEn {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void crearDeposito(Pooled pooled, Segregada segregada, double saldo, Referencia referencia) throws PooledException, SegregadaException, SaldoNoSuficiente {

        Pooled pooled1 = em.find(Pooled.class, pooled);
        Segregada segregada1 = em.find(Segregada.class,segregada);
        Referencia referencia1 = em.find(Referencia.class, referencia);

        if(!pooled1.equals(pooled) || pooled1 == null){
            throw new PooledException("La cuenta: " + pooled + " no existe");
        }

        if(!segregada1.equals(segregada) || segregada1 == null){
            throw new SegregadaException("La cuenta: " + segregada + " no existe");
        }

        if(!referencia.getIban().equals(segregada1.getIban())){
            throw  new SegregadaException("La cuenta: " + segregada + " no existe");
        }

        if(referencia1.getSaldo() < saldo){
            throw new SaldoNoSuficiente("El saldo de la cuenta no es suficiente");
        }



    }


    /*
    @Override
    public void obtenerPersonal(String usuario, String password)
        throws  UsuarioNoEncontradoException, UsuarioIncorrectoException, ContraseñaIncorrectaException {

        Usuario user1 = em.find(Usuario.class,usuario);

        if(user1 == null){
            throw new UsuarioNoEncontradoException("El usuario : " + usuario + " no ha sido encontrado");
        }
        if(user1.getEsAdmin() == false){
            throw new UsuarioIncorrectoException("El usuario: " + usuario + " no es administrador");
        }

        if(user1.getContrasena().isEmpty() || user1.getContrasena() != password || password == null){
            throw new ContraseñaIncorrectaException("La contraseña no es correcta");
        }

    }

     */
}
