package ma3s.fintech;

import ma3s.fintech.excepciones.CuentaExistenteException;
import ma3s.fintech.excepciones.ErrorOrigenTransaccionException;
import ma3s.fintech.excepciones.PersonaNoExisteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Transferencias implements GestionTransferencia{

    @PersistenceContext(name="fintech")
    private EntityManager em;

    @Override
    public void transferenciaClienteAutorizado(Long id, String IBAN_origen, String IBAN_destino, double cantidad) throws PersonaNoExisteException, CuentaExistenteException, ErrorOrigenTransaccionException {
        // Comprobar que el id es una cuenta o persona física o persona
        Empresa empresa = em.find(Empresa.class, id);
        Cliente cliente = em.find(Cliente.class, id);

        if (empresa == null && cliente == null) {
            throw new PersonaNoExisteException("No existe el cliente con id: " + id + ".");
        }

        // Comprobar que la PAutorizada es sobre persona física
        if(cliente != null){

        }

        // Comprobar que estamos sobre una empresa
        if(empresa != null){
            if(!empresa.getCuentasFintech().contains(IBAN_origen)){
                throw new ErrorOrigenTransaccionException("El cliente no tiene acceso a la cuenta: " + IBAN_origen + ".");
            }



        }

    }
}
