package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AperturaCuenta implements GestionAperturaCuenta{
    @PersistenceContext(name="fintech")
    private EntityManager em;

    public void comprobarAdministrador(String usuario) throws UsuarioIncorrectoException, UsuarioNoEncontradoException{
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException();
        }
    }

    public void abrirCuentaPooled(String iban, String swift) throws CuentaExistenteException{
        Cuenta cuenta = em.find(Cuenta.class, iban);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya existe.");
        }

        Pooled pooled = new Pooled();
        pooled.setIban(iban);
        pooled.setSwift(swift);
        pooled.setSaldo(0.0);
    }

    public void abrirCuentaSegregate(String iban, String swift) throws CuentaExistenteException{
        Cuenta cuenta = em.find(Cuenta.class, iban);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya extiste.");
        }

        Segregada segregate = new Segregada();
        segregate.setIban(iban);
        segregate.setSwift(swift);
        segregate.setComision(0.0);
    }
}
