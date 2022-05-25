package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class Transferencias implements GestionTransferencia{
//
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;
   
    /*
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
    */


    @Override
    public List<Transaccion> verTransferencias(String iban) throws CuentaNoExistenteException{
        Query query = em.createQuery("select t from Transaccion t");
        List<Transaccion> listaT = query.getResultList();
        List<Transaccion> listaux = new ArrayList<>();
        Cuenta aux = em.find(Cuenta.class, iban);
        if(aux == null)
            throw new CuentaNoExistenteException();

        for(Transaccion t : listaT){
            if(t.getCuentaOrigen().getIban().equals(iban) || t.getCuentaDestino().getIban().equals(iban)){
                listaux.add(t);
            }
        }

        return listaux;

    }


    @Override
    public List<Transaccion> verTransferencias(Segregada segregada){
        List<Transaccion> aux = new ArrayList<>();
        List<Transaccion> transaccionList = em.createQuery("select p from Transaccion p", Transaccion.class).getResultList();
        if(segregada == null){
            return  aux;
        }
        for(Transaccion auxiliar : transaccionList){
            if(auxiliar.getCuentaOrigen().getIban().equals(segregada.getIban()) ||
                    auxiliar.getCuentaDestino().getIban().equals(segregada.getIban())){
                aux.add(auxiliar);
            }
        }
        return aux;

    }

    @Override
    public List<Transaccion> verTransferencias2(Pooled pooled){
        List<Transaccion> aux = new ArrayList<>();
        List<Transaccion> transaccionList = em.createQuery("select p from Transaccion p", Transaccion.class).getResultList();
        if(pooled == null){
            return aux;
        }
        for(Transaccion auxiliar : transaccionList){
            if(auxiliar.getCuentaOrigen().getIban().equals(pooled.getIban()) ||
                    auxiliar.getCuentaDestino().getIban().equals(pooled.getIban())){
                aux.add(auxiliar);
            }
        }
        return aux;

    }

    @Override
    public void crearTransaccion(Transaccion t) throws CampoVacioException{
        if(t.getFechaInstruccion() == null || t.getCantidad() == null || t.getTipo() == null)
            throw new CampoVacioException();

        String iban_Origen = t.getCuentaOrigen().getIban();
        String iban_Destino = t.getCuentaDestino().getIban();
        Divisa divisa_Origen = t.getDivisaEmisor();
        Divisa divisa_Destino = t.getDivisaReceptor();
        Double saldo = t.getCantidad();
        Double saldo_cambioOrigen = saldo;
        Double saldo_cambioDestino = saldo;


        if (t.getTipo().equals("Pago")) {
            Segregada segregada_origen = em.find(Segregada.class, iban_Origen);
            Pooled pooled_origen = em.find(Pooled.class, iban_Origen);

            if (segregada_origen != null) {
                Referencia referencia_Origen = segregada_origen.getReferencia();
                if (divisa_Origen.equals(referencia_Origen.getDivisa())) {
                    referencia_Origen.setSaldo(referencia_Origen.getSaldo() - saldo);
                } else {
                    saldo_cambioOrigen = saldo * divisa_Origen.getCambioEuro();
                    referencia_Origen.setSaldo(referencia_Origen.getSaldo() - saldo_cambioOrigen);
                }
                em.merge(referencia_Origen);
            } else if (pooled_origen != null) {
                Query query = em.createQuery("select d from DepositadaEn d");
                List<DepositadaEn> listaTodos = query.getResultList();
                List<DepositadaEn> listaOrigen = new ArrayList<>();

                for (DepositadaEn d : listaTodos) {
                    if (d.getIbanPooled().getIban().equals(iban_Origen))
                        listaOrigen.add(d);
                }

                if (listaOrigen.isEmpty())
                    throw new CampoVacioException();
                DepositadaEn depositadaOrigen = null;
                for (DepositadaEn d : listaOrigen) {
                    if (d.getIbanReferencia().getDivisa().equals(divisa_Origen))
                        depositadaOrigen = d;
                }

                if (depositadaOrigen != null) {
                    depositadaOrigen.setSaldo(depositadaOrigen.getSaldo() - saldo);
                    Referencia referenciaOrigen = depositadaOrigen.getIbanReferencia();
                    referenciaOrigen.setSaldo(referenciaOrigen.getSaldo() - saldo);
                    em.merge(depositadaOrigen);
                    em.merge(referenciaOrigen);
                } else {
                    depositadaOrigen = listaOrigen.get(0);
                    saldo_cambioOrigen = saldo * depositadaOrigen.getReferencia().getDivisa().getCambioEuro();
                    depositadaOrigen.setSaldo(depositadaOrigen.getSaldo() - saldo_cambioOrigen);
                    Referencia referenciaOrigen = depositadaOrigen.getIbanReferencia();
                    referenciaOrigen.setSaldo(referenciaOrigen.getSaldo() - saldo);
                    em.merge(depositadaOrigen);
                    em.merge(referenciaOrigen);
                }
            }

            Segregada segregada_destino = em.find(Segregada.class, iban_Destino);
            Pooled pooled_destino = em.find(Pooled.class, iban_Destino);

            if (segregada_destino != null) {
                Referencia referencia_Destino = segregada_destino.getReferencia();
                if (divisa_Destino.equals(referencia_Destino.getDivisa())) {
                    referencia_Destino.setSaldo(referencia_Destino.getSaldo() + saldo);
                } else {
                    saldo_cambioDestino = saldo * divisa_Destino.getCambioEuro();
                    referencia_Destino.setSaldo(referencia_Destino.getSaldo() + saldo_cambioDestino);
                }
                em.merge(referencia_Destino);
            } else if (pooled_destino != null) {
                Query query = em.createQuery("select d from DepositadaEn d");
                List<DepositadaEn> listaTodos = query.getResultList();
                List<DepositadaEn> listaDestino = new ArrayList<>();

                for (DepositadaEn d : listaTodos) {
                    if (d.getIbanPooled().getIban().equals(iban_Destino))
                        listaDestino.add(d);
                }

                if (listaDestino.isEmpty())
                    throw new CampoVacioException();
                DepositadaEn depositadaDestino = null;
                for (DepositadaEn d : listaDestino) {
                    if (d.getIbanReferencia().getDivisa().equals(divisa_Destino))
                        depositadaDestino = d;
                }

                if (depositadaDestino != null) {
                    depositadaDestino.setSaldo(depositadaDestino.getSaldo() + saldo);
                    Referencia referenciaDestino = depositadaDestino.getIbanReferencia();
                    referenciaDestino.setSaldo(referenciaDestino.getSaldo() + saldo);
                    em.merge(depositadaDestino);
                    em.merge(referenciaDestino);
                } else {
                    depositadaDestino = listaDestino.get(0);
                    saldo_cambioDestino = saldo * depositadaDestino.getReferencia().getDivisa().getCambioEuro();
                    depositadaDestino.setSaldo(depositadaDestino.getSaldo() + saldo_cambioDestino);
                    Referencia referenciaDestino = depositadaDestino.getIbanReferencia();
                    referenciaDestino.setSaldo(referenciaDestino.getSaldo() + saldo);
                    em.merge(depositadaDestino);
                    em.merge(referenciaDestino);
                }
            }
        }else if(t.getTipo().equals("Cobro")){
            Segregada segregada_origen = em.find(Segregada.class, iban_Origen);
            Pooled pooled_origen = em.find(Pooled.class, iban_Origen);

            if (segregada_origen != null) {
                Referencia referencia_Origen = segregada_origen.getReferencia();
                if (divisa_Origen.equals(referencia_Origen.getDivisa())) {
                    referencia_Origen.setSaldo(referencia_Origen.getSaldo() + saldo);
                } else {
                    saldo_cambioOrigen = saldo * divisa_Origen.getCambioEuro();
                    referencia_Origen.setSaldo(referencia_Origen.getSaldo() + saldo_cambioOrigen);
                }
                em.merge(referencia_Origen);
            } else if (pooled_origen != null) {
                Query query = em.createQuery("select d from DepositadaEn d");
                List<DepositadaEn> listaTodos = query.getResultList();
                List<DepositadaEn> listaOrigen = new ArrayList<>();

                for (DepositadaEn d : listaTodos) {
                    if (d.getIbanPooled().getIban().equals(iban_Origen))
                        listaOrigen.add(d);
                }

                if (listaOrigen.isEmpty())
                    throw new CampoVacioException();
                DepositadaEn depositadaOrigen = null;
                for (DepositadaEn d : listaOrigen) {
                    if (d.getIbanReferencia().getDivisa().equals(divisa_Origen))
                        depositadaOrigen = d;
                }

                if (depositadaOrigen != null) {
                    depositadaOrigen.setSaldo(depositadaOrigen.getSaldo() + saldo);
                    Referencia referenciaOrigen = depositadaOrigen.getIbanReferencia();
                    referenciaOrigen.setSaldo(referenciaOrigen.getSaldo() + saldo);
                    em.merge(depositadaOrigen);
                    em.merge(referenciaOrigen);
                } else {
                    depositadaOrigen = listaOrigen.get(0);
                    saldo_cambioOrigen = saldo * depositadaOrigen.getReferencia().getDivisa().getCambioEuro();
                    depositadaOrigen.setSaldo(depositadaOrigen.getSaldo() + saldo_cambioOrigen);
                    Referencia referenciaOrigen = depositadaOrigen.getIbanReferencia();
                    referenciaOrigen.setSaldo(referenciaOrigen.getSaldo() + saldo);
                    em.merge(depositadaOrigen);
                    em.merge(referenciaOrigen);
                }
            }

            Segregada segregada_destino = em.find(Segregada.class, iban_Destino);
            Pooled pooled_destino = em.find(Pooled.class, iban_Destino);

            if (segregada_destino != null) {
                Referencia referencia_Destino = segregada_destino.getReferencia();
                if (divisa_Destino.equals(referencia_Destino.getDivisa())) {
                    referencia_Destino.setSaldo(referencia_Destino.getSaldo() - saldo);
                } else {
                    saldo_cambioDestino = saldo * divisa_Destino.getCambioEuro();
                    referencia_Destino.setSaldo(referencia_Destino.getSaldo() - saldo_cambioDestino);
                }
                em.merge(referencia_Destino);
            } else if (pooled_destino != null) {
                Query query = em.createQuery("select d from DepositadaEn d");
                List<DepositadaEn> listaTodos = query.getResultList();
                List<DepositadaEn> listaDestino = new ArrayList<>();

                for (DepositadaEn d : listaTodos) {
                    if (d.getIbanPooled().getIban().equals(iban_Destino))
                        listaDestino.add(d);
                }

                if (listaDestino.isEmpty())
                    throw new CampoVacioException();
                DepositadaEn depositadaDestino = null;
                for (DepositadaEn d : listaDestino) {
                    if (d.getIbanReferencia().getDivisa().equals(divisa_Destino))
                        depositadaDestino = d;
                }

                if (depositadaDestino != null) {
                    depositadaDestino.setSaldo(depositadaDestino.getSaldo() - saldo);
                    Referencia referenciaDestino = depositadaDestino.getIbanReferencia();
                    referenciaDestino.setSaldo(referenciaDestino.getSaldo() - saldo);
                    em.merge(depositadaDestino);
                    em.merge(referenciaDestino);
                } else {
                    depositadaDestino = listaDestino.get(0);
                    saldo_cambioDestino = saldo * depositadaDestino.getReferencia().getDivisa().getCambioEuro();
                    depositadaDestino.setSaldo(depositadaDestino.getSaldo() - saldo_cambioDestino);
                    Referencia referenciaDestino = depositadaDestino.getIbanReferencia();
                    referenciaDestino.setSaldo(referenciaDestino.getSaldo() - saldo);
                    em.merge(depositadaDestino);
                    em.merge(referenciaDestino);
                }
            }
        }
        em.persist(t);
    }

    @Override
    public void transferenciaCliente(Transaccion transaccion, Long id) throws PersonaNoExisteException, CampoVacioException, ErrorOrigenTransaccionException, SaldoNoSuficiente {
        // Vamos a comprobar que el usuario (id) es un cliente
        Cliente cliente = em.find(Cliente.class, id);
        if(cliente == null) throw new PersonaNoExisteException("El cliente no existe");


        // Comprobamos que los campos de la Transferencia nullable = false, no lo sean
        if ( transaccion.getFechaInstruccion() == null
                || transaccion.getCantidad() == null || transaccion.getTipo() == null){
            throw new CampoVacioException("El objeto transaccion tiene algún campo no null a nulo.");
        }

        // Comprobamos que el usuario tiene acceso sobre la cuenta origen
        Cuenta cuentaOri = transaccion.getCuentaOrigen();
        if(!cliente.getCuentasFintech().contains(cuentaOri)) throw new ErrorOrigenTransaccionException("La cuenta de origen no pertenece a este usuario.");

        // Ahora tenemos dos camino uno si la cuenta es Pooled y otro si la cuenta es segregada
        Pooled pooled = em.find(Pooled.class, cuentaOri.getIban());
        if(pooled != null){
            Divisa divisaPooled = pooled.getDivisa();

            int i = 0;
            boolean encontrado = false;
            while (i < pooled.getDepositos().size() && !encontrado ){
                if(pooled.getDepositos().get(i).getReferencia().getDivisa().equals(divisaPooled)){
                    encontrado = true;
                }else{
                    i++;
                }
            }

            if(!encontrado) throw new ErrorOrigenTransaccionException("La cuenta pooled no tiene cuenta de referencia para esa divisa.");

            Referencia referencia = pooled.getDepositos().get(i).getReferencia();
            Divisa divisaOri = pooled.getDivisa();
            Divisa divisaRecep = referencia.getDivisa();

            // Si la cantidad es menor que 0 es un cargo, en otro caso es un ingreso
            if(transaccion.getCantidad() < 0){
                Double cargo = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if(!divisaRecep.equals(divisaOri)){
                    cargo = cargo * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                }

                if(referencia.getSaldo() < Math.abs(cargo)) throw new SaldoNoSuficiente("La cuenta de referencia no tiene suficiente saldo");

                referencia.setSaldo(referencia.getSaldo() - cargo);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                em.persist(transaccion);
                // Es un ingreso
            }else {
                Double ingreso = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if (!divisaRecep.equals(divisaOri)) {
                    ingreso = ingreso * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                }

                referencia.setSaldo(referencia.getSaldo() + ingreso);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                em.persist(transaccion);
            }


        }

        // Buscamos si es una segregada
        Segregada segregada = em.find(Segregada.class, cuentaOri.getIban());
        if(segregada != null){
            // Obtenemos la cuenta de referencia de la cuenta segregada
            Referencia referencia = segregada.getReferencia();
            Double comision = segregada.getComision();

            // Cogemos las divisas
            Divisa divisaOri = transaccion.getDivisaEmisor(); // Divisa de la cuenta Externa a Fintech
            Divisa divisaRecep = referencia.getDivisa();      // Divisa de la cuenta Fintech

            // Si la cantidad es menor que 0 es un cargo, en otro caso es un ingreso
            if(transaccion.getCantidad() < 0){
                Double cargo = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if(divisaRecep.equals(divisaOri)){
                    cargo = cargo * (1 + comision);
                }else{
                    cargo = cargo * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                    cargo = cargo * (1 + comision);
                }

                if(referencia.getSaldo() < Math.abs(cargo)) throw new SaldoNoSuficiente("La cuenta de referencia no tiene suficiente saldo");

                referencia.setSaldo(referencia.getSaldo() - cargo);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                transaccion.setComision(segregada.getComision());
                em.persist(transaccion);
            // Es un ingreso
            }else {
                Double ingreso = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if (divisaRecep.equals(divisaOri)) {
                    ingreso = ingreso * (1 - comision);
                } else {
                    ingreso = ingreso * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                    ingreso = ingreso * (1 - comision);
                }

                referencia.setSaldo(referencia.getSaldo() + ingreso);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                transaccion.setComision(segregada.getComision());
                em.persist(transaccion);
            }
        }



    }

    @Override
    public void transferenciaAutorizado(Long id, Long idEmpresa, Transaccion transaccion) throws PersonaNoExisteException, ErrorOrigenTransaccionException,
            CampoVacioException, SaldoNoSuficiente, EmpresaNoExistenteException {
        // Comprobar que la PAutorizada existe
        PAutorizada pAutorizada = em.find(PAutorizada.class, id);
        if(pAutorizada == null) throw new PersonaNoExisteException("La persona autorizada con id: " + id + " no existe.");

        // Comprobar que la empresa existe
        Empresa empresa = em.find(Empresa.class, idEmpresa);
        if(empresa == null) throw new EmpresaNoExistenteException("La empresa con id: " + id + " no existe.");

        // Comprobamos que los campos de la Transferencia nullable = false, no lo sean
        if ( transaccion.getIdUnico() == null || transaccion.getFechaInstruccion() == null
                || transaccion.getCantidad() == null || transaccion.getTipo() == null){
            throw new CampoVacioException("El objeto transaccion tiene algún campo no null a nulo.");
        }

        // Comprobar que esta persona autorizada tiene autorizacion en la empresa
        /*AutorizadaId autorizadaID = new AutorizadaId();
        autorizadaID.setIdAutorizado(id);
        autorizadaID.setEmpresaId(idEmpresa);
        Autorizacion autorizacion = em.find(Autorizacion.class, autorizadaID);*/

        boolean encontrado = false;
        int i = 0;
        while (i < pAutorizada.getAutorizaciones().size() && !encontrado){
            if(pAutorizada.getAutorizaciones().get(i).getEmpresaId().equals(empresa)) {
                encontrado = true;
            }else{
                i++;
            }
        }

        if(!encontrado) throw new PersonaNoExisteException("La PAutorizada no tiene autorización sobre la empresa");

        Cuenta cuentaOri = transaccion.getCuentaOrigen();
        if(!empresa.getCuentasFintech().contains(cuentaOri)) throw new ErrorOrigenTransaccionException("La empresa no tiene ninguna cuenta Fintech con ese IBAN");

        // Ahora tenemos dos camino uno si la cuenta es Pooled y otro si la cuenta es segregada
        Pooled pooled = em.find(Pooled.class, cuentaOri.getIban());
        if(pooled != null){
            Divisa divisaPooled = pooled.getDivisa();

            int n = 0;
            boolean enc = false;
            while (n < pooled.getDepositos().size() && !enc ){
                if(pooled.getDepositos().get(i).getReferencia().getDivisa().equals(divisaPooled)){
                    encontrado = true;
                }else{
                    n++;
                }
            }

            if(!encontrado) throw new ErrorOrigenTransaccionException("La cuenta pooled no tiene cuenta de referencia para esa divisa.");

            Referencia referencia = pooled.getDepositos().get(i).getReferencia();
            Divisa divisaOri = pooled.getDivisa();
            Divisa divisaRecep = referencia.getDivisa();

            // Si la cantidad es menor que 0 es un cargo, en otro caso es un ingreso
            if(transaccion.getCantidad() < 0){
                Double cargo = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if(!divisaRecep.equals(divisaOri)){
                    cargo = cargo * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                }

                if(referencia.getSaldo() < Math.abs(cargo)) throw new SaldoNoSuficiente("La cuenta de referencia no tiene suficiente saldo");

                referencia.setSaldo(referencia.getSaldo() - cargo);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                em.persist(transaccion);
                // Es un ingreso
            }else {
                Double ingreso = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if (!divisaRecep.equals(divisaOri)) {
                    ingreso = ingreso * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                }

                referencia.setSaldo(referencia.getSaldo() + ingreso);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                em.persist(transaccion);
            }


        }

        // Buscamos si es una segregada
        Segregada segregada = em.find(Segregada.class, cuentaOri.getIban());
        if(segregada != null){
            // Obtenemos la cuenta de referencia de la cuenta segregada
            Referencia referencia = segregada.getReferencia();
            Double comision = segregada.getComision();

            // Cogemos las divisas
            Divisa divisaOri = transaccion.getDivisaEmisor(); // Divisa de la cuenta Externa a Fintech
            Divisa divisaRecep = referencia.getDivisa();      // Divisa de la cuenta Fintech

            // Si la cantidad es menor que 0 es un cargo, en otro caso es un ingreso
            if(transaccion.getCantidad() < 0){
                Double cargo = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if(divisaRecep.equals(divisaOri)){
                    cargo = cargo * (1 + comision);
                }else{
                    cargo = cargo * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                    cargo = cargo * (1 + comision);
                }

                if(referencia.getSaldo() < Math.abs(cargo)) throw new SaldoNoSuficiente("La cuenta de referencia no tiene suficiente saldo");

                referencia.setSaldo(referencia.getSaldo() - cargo);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                transaccion.setComision(segregada.getComision());
                em.persist(transaccion);
                // Es un ingreso
            }else {
                Double ingreso = transaccion.getCantidad();
                // ¿Las divisas son las mismas?
                if (divisaRecep.equals(divisaOri)) {
                    ingreso = ingreso * (1 - comision);
                } else {
                    ingreso = ingreso * divisaRecep.getCambioEuro() / divisaOri.getCambioEuro();
                    ingreso = ingreso * (1 - comision);
                }

                referencia.setSaldo(referencia.getSaldo() + ingreso);
                em.merge(referencia);
                transaccion.setFechaEjecucion(new Date());
                transaccion.setComision(segregada.getComision());
                em.persist(transaccion);
            }
        }



    }

    /*
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

    */

    /*
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
    */
}
