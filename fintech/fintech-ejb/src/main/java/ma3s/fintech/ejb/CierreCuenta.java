package ma3s.fintech.ejb;

import ma3s.fintech.Cuenta;
import ma3s.fintech.Referencia;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CierreCuenta implements GestionCierreCuenta{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    //@Override
    private void comprobarAdministrador(String usuario) throws UsuarioIncorrectoException, UsuarioNoEncontradoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException();
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException();
        }
    }

    @Override
    public void cerrarCuenta(String iban, String usuario) throws CuentaNoExistenteException, CuentaNoVacia, UsuarioNoEncontradoException, UsuarioIncorrectoException {
        Cuenta cuenta = em.find(Cuenta.class, iban);
        Referencia referencia = em.find(Referencia.class, iban);

        comprobarAdministrador(usuario);

        if(cuenta == null || referencia == null){
            throw new CuentaNoExistenteException("La cuenta con IBAN "+iban+" no existe.");
        }

        if(referencia.getSaldo() != 0){
            throw new CuentaNoVacia("La cuenta con IBAN "+iban+" no tiene el saldo a 0.");
        }

        referencia.setEstado("Cerrado");
        em.merge(referencia);
    }
}
