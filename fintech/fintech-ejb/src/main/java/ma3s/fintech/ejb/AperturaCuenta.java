package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.CuentaExistenteException;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

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
    public void abrirCuentaPooled(String iban, String swift, String usuario, String divisaAbrev) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException {
        Cuenta cuenta = em.find(Cuenta.class, iban);

        comprobarAdministrador(usuario);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya existe.");
        }

        Divisa divisa = em.find(Divisa.class, divisaAbrev);
        if(divisa == null) throw new DivisaExistenteException("La divisa no existe");

        Pooled pooled = new Pooled();
        pooled.setIban(iban);
        pooled.setSwift(swift);
        pooled.setEstado("Abierta");
        pooled.setDivisa(divisa);
        pooled.setFechaApertura(new Date());
        pooled.setSaldo(0.0);

        em.persist(pooled);
    }

    @Override
    public void abrirCuentaSegregate(String iban, String swift, String usuario) throws CuentaExistenteException, UsuarioNoEncontradoException, UsuarioIncorrectoException {
        Cuenta cuenta = em.find(Cuenta.class, iban);

        comprobarAdministrador(usuario);

        if(cuenta != null){
            throw new CuentaExistenteException("La cuenta con IBAN "+iban+" ya extiste.");
        }

        Segregada segregate = new Segregada();
        segregate.setIban(iban);
        segregate.setSwift(swift);
        segregate.setEstado("Abierta");
        segregate.setFechaApertura(new Date());
        segregate.setComision(0.0);

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



}
