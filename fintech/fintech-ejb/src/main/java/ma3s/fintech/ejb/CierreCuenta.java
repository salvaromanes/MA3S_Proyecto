package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class CierreCuenta implements GestionCierreCuenta{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    private void comprobarAdministrador(String usuario) throws UsuarioIncorrectoException, UsuarioNoEncontradoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException("El usuario "+usuario+" no ha sido encontrado");
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException("El usuario "+usuario+" no es administrador");
        }
    }

    @Override
    public void cerrarCuenta(String iban, String usuario) throws CuentaNoExistenteException, CuentaNoVacia, UsuarioNoEncontradoException, UsuarioIncorrectoException {
        comprobarAdministrador(usuario);

        Segregada s = em.find(Segregada.class, iban);
        Pooled p = em.find(Pooled.class, iban);

        if(s == null && p == null)
            throw new CuentaNoExistenteException("La cuenta no existe");

        if(p != null){
            Query query = em.createQuery("select d from DepositadaEn d");
            List<DepositadaEn> depositadas = query.getResultList();
            for(DepositadaEn d : depositadas){
                if(d.getIbanPooled().equals(p) && d.getSaldo() != 0)
                    throw new CuentaNoVacia("La cuenta no tiene saldo 0");
            }
            p.setEstado("Cerrada");
            em.merge(p);
        }

        if(s != null){
            if(s.getReferencia().getSaldo() != 0)
                throw new CuentaNoVacia("La cuenta no tiene saldo 0");
            s.setEstado("Cerrada");
            s.setFechaCierre(new Date());
            em.merge(s);
        }

    }
}
