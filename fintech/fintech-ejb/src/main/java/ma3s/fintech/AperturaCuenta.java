package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AperturaCuenta implements GestionAperturaCuenta{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    public void comprobarAdministrador(String usuario) throws UsuarioIncorrectoException, UsuarioNoEncontradoException{
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException();
        }
    }

    public void abrirCuentaPooled(String iban, String swift, String usuario) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException {
        Cuenta cuenta = em.find(Cuenta.class, iban);

        comprobarAdministrador(usuario);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya existe.");
        }

        Pooled pooled = new Pooled();
        pooled.setIban(iban);
        pooled.setSwift(swift);
        pooled.setSaldo(0.0);

        em.persist(pooled);
    }

    public void abrirCuentaSegregate(String iban, String swift, String usuario) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException {
        Cuenta cuenta = em.find(Cuenta.class, iban);

        comprobarAdministrador(usuario);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya extiste.");
        }

        Segregada segregate = new Segregada();
        segregate.setIban(iban);
        segregate.setSwift(swift);
        segregate.setComision(0.0);

        em.persist(segregate);
    }
}
