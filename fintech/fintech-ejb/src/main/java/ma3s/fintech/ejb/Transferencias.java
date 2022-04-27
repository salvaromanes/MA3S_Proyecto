package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless
public class Transferencias implements GestionTransferencia{

    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    private Transaccion subirTransaccion(String idTransf, Date fechaInstruccion, Double cantidad, String tipo, Double comision, Boolean internacional,
                                         Cuenta cuentaOri, Cuenta cuentaDest, Divisa divisaOri, Divisa divisaDest){
        Transaccion transaccion = new Transaccion();

        transaccion.setIdUnico(idTransf);
        transaccion.setFechaInstruccion(fechaInstruccion);
        Date date = new Date();
        transaccion.setFechaEjecucion(date);
        transaccion.setCantidad(cantidad);
        //transaccion.setTipo();
        transaccion.setComision(comision);

        return transaccion;
    }

    @Override
    public void transferenciaCliente(Long id, String idTransaccion, String IBAN_origen, String IBAN_destino, double cantidad, String divisaOrigen, Date fechaInstruccion)
    throws PersonaNoExisteException, CuentaExistenteException, ErrorOrigenTransaccionException, TransaccionYaExistente, SaldoNoSuficiente {
        // Comprobamos que existe el cliente
        Cliente cliente = em.find(Cliente.class, id);
        if (cliente == null) {
            throw new PersonaNoExisteException("No existe el cliente con id: " + id + ".");
        }

        // Buscamos la cuenta del cliente con el IBAN que tenemos como parametro
        Fintech cuenta = em.find(Fintech.class, IBAN_origen);
        if(cuenta == null) throw new CuentaExistenteException("La cuenta origen con IBAN: " + IBAN_origen + " no existe.");

        // El cliente no tiene acceso a esta cuenta origen
        if(!cliente.getCuentasFintech().contains(cuenta)) throw new ErrorOrigenTransaccionException("El cliente con id " + id.toString() +
                " no tiene acceso sobre la cuenta origen con IBAN: " + IBAN_origen);

        // La transaccion ya esta creada
        Transaccion transaccion = em.find(Transaccion.class, idTransaccion);
        if (transaccion != null) throw new TransaccionYaExistente("La transaccion con id: " + idTransaccion + " ya existe.");

        // Buscamos si la cuenta es Pooled ->> No podemos seguir
        Pooled pooled = em.find(Pooled.class, IBAN_origen);
        if(pooled != null){

        }

        Divisa divisaDest = null, divisaOri = null;

        // Buscamos si es una segregada
        Segregada segregada = em.find(Segregada.class, IBAN_origen);
        if(segregada != null) {
            // Obtenemos la cuenta de referencia de la cuenta segregada
            Referencia referencia = segregada.getReferencia();
            Double comision = segregada.getComision();

            // Obtenemos la divisa de la cuenta de referencia
            divisaDest = referencia.getDivisa();
            divisaOri = em.find(Divisa.class, divisaOrigen);

            if(cantidad < 0){ // Cargo
                if(divisaDest.equals(divisaOri)){
                    cantidad = cantidad * (1 + comision);

                    if(referencia.getSaldo() < cantidad) throw new SaldoNoSuficiente("El saldo de la cuenta no es suficiente para poder realizar el cobro");

                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }else {
                    cantidad = cantidad * divisaDest.getCambioEuro() * divisaOri.getCambioEuro();
                    cantidad = cantidad * (1 + comision);

                    if(referencia.getSaldo() < cantidad) throw new SaldoNoSuficiente("El saldo de la cuenta no es suficiente para poder realizar el cobro");

                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }
            }else{ // Ingreso
                if(divisaDest.equals(divisaOri)){
                    cantidad = cantidad * (1 - comision);

                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }else {
                    cantidad = cantidad * divisaDest.getCambioEuro() * divisaOri.getCambioEuro();
                    cantidad = cantidad * (1 - comision);

                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }
            }
        }


        Transaccion trans = subirTransaccion(idTransaccion, fechaInstruccion, cantidad, null, segregada.getComision(), false, cuenta,
                null, divisaOri, divisaDest);
        em.persist(trans);

    }


    @Override
    public void transferenciaAutorizado(Long id, String idTransaccion, String IBAN_origen, String IBAN_destino, double cantidad, String divisaOrigen, Long idEmpresa,
                                        Date fechaInstruccion)
    throws PersonaNoExisteException, CuentaExistenteException, ErrorOrigenTransaccionException, TransaccionYaExistente, SaldoNoSuficiente {
        // Comprobar que la PAutorizada existe
        PAutorizada pAutorizada = em.find(PAutorizada.class, id);
        if(pAutorizada == null) throw new PersonaNoExisteException("La persona autorizada con id: " + id + " no existe.");

        // Comprobar que la empresa existe
        Empresa empresa = em.find(Empresa.class, idEmpresa);
        if(empresa == null) throw new PersonaNoExisteException("La empresa con id: " + id + " no existe.");

        // Comprobar que esta persona autorizada tiene autorizacion en la empresa
        AutorizadaId autorizadaID = new AutorizadaId();
        autorizadaID.setIdAutorizado(id);
        autorizadaID.setEmpresaId(idEmpresa);
        Autorizacion autorizacion = em.find(Autorizacion.class, autorizadaID);
        if(autorizacion == null) throw new PersonaNoExisteException("La empresa con id: " + idEmpresa + " no existe.");

        // Buscamos la cuenta de la empresa con el IBAN que tenemos como parametro
        Fintech cuenta = em.find(Fintech.class, IBAN_origen);
        if(cuenta == null) throw new CuentaExistenteException("La cuenta origen con IBAN: " + IBAN_origen + " no existe.");

        // La empresa no tiene acceso a esta cuenta origen
        if(!empresa.getCuentasFintech().contains(cuenta)) throw new ErrorOrigenTransaccionException("La empresa con id " + id.toString() +
                " no tiene acceso sobre la cuenta origen con IBAN: " + IBAN_origen);

        // La transaccion ya esta creada
        Transaccion transaccion = em.find(Transaccion.class, idTransaccion);
        if (transaccion != null) throw new TransaccionYaExistente("La transaccion con id: " + idTransaccion + " ya existe.");

        // Buscamos si la cuenta es Pooled ->> No podemos seguir
        Pooled pooled = em.find(Pooled.class, IBAN_origen);
        if(pooled != null){

        }

        Divisa divisaDest = null, divisaOri = null;

        // Buscamos si es una segregada
        Segregada segregada = em.find(Segregada.class, IBAN_origen);
        if(segregada != null) {
            // Obtenemos la cuenta de referencia de la cuenta segregada
            Referencia referencia = segregada.getReferencia();
            Double comision = segregada.getComision();

            // Obtenemos la divisa de la cuenta de referencia
            divisaDest = referencia.getDivisa();
            divisaOri = em.find(Divisa.class, divisaOrigen);

            if(cantidad < 0){ // Cargo
                if(divisaDest.equals(divisaOri)){
                    cantidad = cantidad * (1 + comision);
                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }else {
                    cantidad = cantidad * divisaDest.getCambioEuro() * divisaOri.getCambioEuro();
                    cantidad = cantidad * (1 + comision);

                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }
            }else{ // Ingreso
                if(divisaDest.equals(divisaOri)){
                    cantidad = cantidad * (1 - comision);
                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }else {
                    cantidad = cantidad * divisaDest.getCambioEuro() * divisaOri.getCambioEuro();
                    cantidad = cantidad * (1 - comision);

                    referencia.setSaldo(referencia.getSaldo() + cantidad);
                    em.merge(referencia);
                }
            }
        }

        Transaccion trans = subirTransaccion(idTransaccion, fechaInstruccion, cantidad, null, segregada.getComision(), false, cuenta,
                null, divisaOri, divisaDest);
        em.persist(trans);

    }

}
