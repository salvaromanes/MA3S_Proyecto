package ma3s.fintech;

import ma3s.fintech.excepciones.ContraseñaIncorrectaException;
import ma3s.fintech.excepciones.CuentaNoExistenteException;
import ma3s.fintech.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


public class GeneracionInfHolanda implements GestionInfHolanda {

    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    @Override
    public List<Cuenta> CuentasAbiertas(Cuenta cuenta) throws CuentaNoExistenteException {
        Cuenta cuenta_Aux = em.find(Cuenta.class, cuenta);
        if(!cuenta_Aux.equals(cuenta)){
            throw new CuentaNoExistenteException("La cuenta : " + cuenta_Aux.getIban() +  " no se encuentra" );
        }
    return null;
    }

}
