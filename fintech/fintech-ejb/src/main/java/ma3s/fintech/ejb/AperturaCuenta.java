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
import java.util.Random;

@Stateless
public class AperturaCuenta implements GestionAperturaCuenta{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    private void comprobarAdministrador(String usuario) throws UsuarioIncorrectoException, UsuarioNoEncontradoException{
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException("El usuario "+usuario+" no ha sido encontrado");
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException("El usuario "+usuario+" no es administrador");
        }
    }

    @Override
    public void abrirCuentaPooled(String iban, String swift, String usuario, String divisaAbrev, String identificacion) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException, ClienteNoExisteException {
        Cuenta cuenta = em.find(Cuenta.class, iban);

        comprobarAdministrador(usuario);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya existe.");
        }

        Divisa divisa = em.find(Divisa.class, divisaAbrev);
        if(divisa == null) throw new DivisaExistenteException("La divisa no existe");

        Cliente cliente;
        Query query = em.createQuery("select c from Cliente c where c.identificacion like :ident").setParameter("ident", identificacion);

        if(query.getResultList() == null){
            throw new ClienteNoExisteException("El cliente no existe");
        }

        if(query.getResultList().size() == 0){
            throw new ClienteNoExisteException("El cliente no existe");
        }

        cliente = (Cliente )query.getResultList().get(0);

        if(cliente == null){
            throw new ClienteNoExisteException("El cliente no existe");
        }

        Pooled pooled = new Pooled();
        pooled.setIban(iban);
        pooled.setSwift(swift);
        pooled.setEstado("Abierta");
        pooled.setDivisa(divisa);
        pooled.setFechaApertura(new Date());
        pooled.setSaldo(0.0);
        pooled.setCliente(cliente);

        em.persist(pooled);
    }

    @Override
    public void abrirCuentaSegregate(String iban, String swift, String usuario, String identificacion) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException, ClienteNoExisteException {
        Cuenta cuenta = em.find(Cuenta.class, iban);

        comprobarAdministrador(usuario);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya extiste.");
        }

        Cliente cliente;
        Query query = em.createQuery("select c from Cliente c where c.identificacion like :ident").setParameter("ident", identificacion);

        if(query.getResultList() == null){
            throw new ClienteNoExisteException("El cliente no existe");
        }

        if(query.getResultList().size() == 0){
            throw new ClienteNoExisteException("El cliente no existe");
        }

        cliente = (Cliente)query.getResultList().get(0);

        if(cliente == null){
            throw new ClienteNoExisteException("El cliente no existe");
        }

        Segregada segregate = new Segregada();
        segregate.setIban(iban);
        segregate.setSwift(swift);
        segregate.setEstado("Abierta");
        segregate.setFechaApertura(new Date());
        segregate.setComision(0.0);
        segregate.setCliente(cliente);

        em.persist(segregate);
    }

    @Override
    public Pooled obtenerDatosCuentaPooled(String IBAN){
        Pooled pooled = em.find(Pooled.class, IBAN);

        if(pooled != null){
            return pooled;
        }

        return null;
    }

    @Override
    public Segregada obtenerDatosCuentaSegregada(String IBAN){
        Segregada segregada = em.find(Segregada.class, IBAN);

        if(segregada != null){
            return segregada;
        }

        return null;
    }

    @Override
    public void referenciaParaPooled(String ibanPooled, String divisaAbrev) throws DivisaExistenteException, PooledException, DatosIncorrectosException {

        if(ibanPooled == null || divisaAbrev == null){
            throw new DatosIncorrectosException("Algun dato como parametro es null");
        }

        // Buscar en las cuentas de referencia si existe el iban que me pasan
            // Si existe -> miro que no este relacionada con una segregada y creo la relacion depositadaEn, en caso contrario lanzo error
            // Si no existe -> creo una cuenta de referencia con esa divisa y hago la relación con esa divisa

        Pooled pooled = em.find(Pooled.class, ibanPooled);
        Divisa divisa = em.find(Divisa.class, divisaAbrev);
        Referencia referencia = null;

        if (pooled == null) throw new PooledException("La cuenta pooled no existe");



        if (divisa == null) throw new DivisaExistenteException("La divisa no existe");

        Query query = em.createQuery("Select c from Referencia c");
        List<Referencia> referencias = query.getResultList();

        int i = 0;
        boolean encontrado = false;
        while(i < referencias.size() && !encontrado){
            if(referencias.get(i).getSegregada() == null && referencias.get(i).getDivisa().equals(divisa)){
                encontrado = true;
                referencia = referencias.get(i);
            }
            i++;
        }

        if(encontrado && referencia != null){ // He encontrado una cuenta de referencia con la misma divisa y no esta relacionada con una segregada
            System.out.println("REFERENCIA:\n" + referencia.toString());
            System.out.println("POOLED:\n" + pooled.toString());

//            GestionDepositadaEn dep = new DepositadaEnAcciones();
//            dep.crearDeposito(pooled, 0.0, referencia);

            DepositadaEn dep = new DepositadaEn();
            dep.setIbanPooled(pooled);
            dep.setReferencia(referencia);
            dep.setSaldo(0.0);
            em.persist(dep);
        }else{
            referencia = new Referencia();
            referencia.setIban(getIban());
            referencia.setFechaApertura(new Date());
            referencia.setEstado("ABIERTA");
            referencia.setDivisa(divisa);
            referencia.setNombreBanco("Ebury");
            referencia.setSucursal("Ebury Málaga");
            referencia.setSwift("NMGHJWYT");
            referencia.setPais("España");
            em.persist(referencia);

            DepositadaEn dep = new DepositadaEn();
            dep.setIbanPooled(pooled);
            dep.setReferencia(referencia);
            dep.setSaldo(0.0);
            em.persist(dep);
        }
    }

    @Override
    public void referenciaParaSegregada(String ibanReferencia, String ibanSegregada, String divisaAbrev) throws SegregadaException, DivisaExistenteException, ReferenciaException, DatosIncorrectosException {
        Segregada segregada = em.find(Segregada.class, ibanSegregada);
        Divisa divisa = em.find(Divisa.class, divisaAbrev);
        Referencia referencia = new Referencia();

        if(ibanReferencia == null || ibanSegregada == null || divisaAbrev == null){
            throw new DatosIncorrectosException("Algun dato como parametro es null");
        }

        if (segregada == null) throw new SegregadaException("La cuenta segregada no existe");
        if (divisa == null) throw new DivisaExistenteException("La divisa no existe");

        Referencia ref = em.find(Referencia.class, ibanReferencia);

        if(ref == null){
            Referencia nuevaReferencia = new Referencia();
            nuevaReferencia.setIban(ibanReferencia);
            nuevaReferencia.setFechaApertura(new Date());
            nuevaReferencia.setEstado("ABIERTA");
            nuevaReferencia.setDivisa(divisa);
            nuevaReferencia.setNombreBanco("Ebury");
            nuevaReferencia.setSucursal("Ebury Málaga");
            nuevaReferencia.setSwift("NMGHJWYT");
            nuevaReferencia.setPais("España");
            nuevaReferencia.setSegregada(segregada);
            em.persist(nuevaReferencia);

            segregada.setReferencia(referencia);
            em.merge(segregada);
        }else{
            throw new ReferenciaException("La cuenta referencia ya tiene una relación con una segregada");
        }
    }

    private String getIban(){
        String res = "ES";
        Random random = new Random();
        int max = 10;
        for (int n = 0; n < 22; n++){
            res += Integer.toString(random.nextInt(max));
        }
        return res;
    }
}
