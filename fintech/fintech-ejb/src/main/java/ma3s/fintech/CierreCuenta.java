package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.CuentaNoExistenteException;
import ma3s.fintech.excepciones.CuentaNoVacia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CierreCuenta implements GestionCierreCuenta{
    @PersistenceContext(name="fintech")
    private EntityManager em;

    public void cerrarCuenta(String iban) throws CuentaNoExistenteException, CuentaNoVacia {
        Cuenta cuenta = em.find(Cuenta.class, iban);

        if(cuenta == null){
            throw new CuentaNoExistenteException("La cuenta con IBAN "+iban+" no existe.");
        }

        DepositadaEn deposito = em.find(DepositadaEn.class, iban);
        if(deposito.getSaldo() != 0){
            throw new CuentaNoVacia("La cuenta con IBAN "+iban+" no tiene el saldo a 0.");
        }

        em.remove(cuenta);
    }
}
