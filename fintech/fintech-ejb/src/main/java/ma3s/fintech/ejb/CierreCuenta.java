package ma3s.fintech.ejb;

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

        Referencia referencia = em.find(Referencia.class, iban);

        if(referencia == null){
            throw new CuentaNoExistenteException("La cuenta referencia no existe");
        }

        if(referencia.getSaldo() != 0){
            throw new CuentaNoVacia("La cuenta con IBAN "+iban+" no tiene el saldo a 0.");
        }

        referencia.setEstado("Cerrado");
        em.merge(referencia);
    }
}
