package ma3s.fintech;

import com.sun.jndi.ldap.pool.Pool;
import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.ErrorOrigenTransaccionException;
import ma3s.fintech.excepciones.PersonaNoExisteException;
import ma3s.fintech.excepciones.TransaccionYaExistente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Transferencias implements GestionTransferencia{

    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    @Override
    public void transferenciaClienteAutorizado(Long id, String idTransaccion, String IBAN_origen, String IBAN_destino, double cantidad) throws PersonaNoExisteException,
    CuentaExistenteException, ErrorOrigenTransaccionException, TransaccionYaExistente {
        // Comprobar que el id es una cuenta o persona física o persona
        Empresa empresa = em.find(Empresa.class, id);
        Cliente cliente = em.find(Cliente.class, id);

        if (empresa == null && cliente == null) {
            throw new PersonaNoExisteException("No existe el cliente con id: " + id + ".");
        }

        // ¿Es un cliente?
        if(cliente != null){
            // Buscamos la cuenta del cliente con el IBAN que tenemos como parametro
            Fintech cuenta = em.find(Fintech.class, IBAN_origen);

            // La cuenta no existe
            if(cuenta == null) throw new CuentaExistenteException("La cuenta origen con IBAN: " + IBAN_origen + " no existe.");

            // El cliente no tiene acceso a esta cuenta origen
            if(!cliente.getCuentasFintech().contains(cuenta)){
                throw new ErrorOrigenTransaccionException("El cliente con id " + id.toString() + " no tiene acceso sobre la cuenta origen con IBAN: " + IBAN_origen);
            }

            // La transaccion ya esta creada
            Transaccion transaccion = em.find(Transaccion.class, idTransaccion);
            if (transaccion != null){
                throw new TransaccionYaExistente("La transaccion con id: " + idTransaccion + " ya existe.");
            }

            // Buscamos si la cuenta es Pooled
            Pooled pooled = em.find(Pooled.class, IBAN_origen);
            if(pooled != null){
                //DepositadaEn depositadaEn = em.find(DepositadaEn.class, )
            }


        }

        // Comprobar que estamos sobre una empresa
        if(empresa != null){
            if(!empresa.getCuentasFintech().contains(IBAN_origen)){
                throw new ErrorOrigenTransaccionException("El cliente no tiene acceso a la cuenta: " + IBAN_origen + ".");
            }



        }

    }
}
