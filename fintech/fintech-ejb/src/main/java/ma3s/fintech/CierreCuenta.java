package ma3s.fintech;

import ma3s.fintech.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CierreCuenta implements GestionCierreCuenta{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    public void comprobarAdministrador(String usuario) throws UsuarioIncorrectoException, UsuarioNoEncontradoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException();
        }
    }

    public void cerrarCuenta(String iban) throws CuentaNoExistenteException, CuentaNoVacia {
        Cuenta cuenta = em.find(Cuenta.class, iban);
        Referencia referencia = em.find(Referencia.class, iban);

        if(cuenta == null || referencia == null){
            throw new CuentaNoExistenteException("La cuenta con IBAN "+iban+" no existe.");
        }

        if(referencia.getSaldo() != 0){
            throw new CuentaNoVacia("La cuenta con IBAN "+iban+" no tiene el saldo a 0.");
        }

        referencia.setEstado("Cerrado");
    }
}
