package ma3s.fintech.war;


import com.sun.jndi.ldap.pool.Pool;
import ma3s.fintech.*;
import ma3s.fintech.ejb.GestionAperturaCuenta;
import ma3s.fintech.ejb.GestionEliminarAutorizados;
import ma3s.fintech.ejb.GestionGetCuentas;
import ma3s.fintech.ejb.excepciones.CuentaExistenteException;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "mostrarDatosCuenta")
@RequestScoped
public class MostrarDatosCuenta {
    private static final Logger LOGGER = Logger.getLogger(InicioSesionIndex.class.getCanonicalName());

    @Inject
    private GestionGetCuentas gestionGetCuentas;

    @Inject
    private GestionEliminarAutorizados gestionEliminarAutorizados;

    @Inject
    private Sesion sesion;

    private List<Pooled> listaPooled = new ArrayList<Pooled>();
    private List<Autorizacion> autorizaciones = new ArrayList<Autorizacion>();
    private List<Segregada> listaSegregada = new ArrayList<Segregada>();

    private Boolean esPooled = false;
    private Boolean esSegregada = false;

    public Boolean getEsSegregada() {
        return esSegregada;
    }
    public void setEsSegregada(Boolean esSegregada) {
        this.esSegregada = esSegregada;
    }

    public List<Pooled> getListaPooled() {
        return listaPooled;
    }
    public void setListaPooled(List<Pooled> listaPooled) {
        this.listaPooled = listaPooled;
    }

    public List<Segregada> getListaSegregada() {
        return listaSegregada;
    }
    public void setListaSegregada(List<Segregada> segregada) {
        this.listaSegregada = segregada;
    }

    public Boolean getEsPooled() {
        return esPooled;
    }
    public void setEsPooled(Boolean esPooled) {
        this.esPooled = esPooled;
    }

    public List<Autorizacion> getAutorizaciones() {
        return autorizaciones;
    }
    public void setAutorizaciones(List<Autorizacion> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    public MostrarDatosCuenta(){
        /*
        Empresa empresa = new Empresa();
        empresa.setId(new Long(1));
        empresa.setIdentificacion("P3310693A");
        empresa.setTipoCliente("Jurídico");
        empresa.setEstado("Activo");
        empresa.setFechaAlta(new Date());
        empresa.setDireccion("Buleavaur");
        empresa.setCiudad("Málaga");
        empresa.setCodigopostal("29004");
        empresa.setPais("Espana");
        empresa.setRazonSocial("UMA");

        Pooled pooled;
        pooled = new Pooled();
        pooled.setCliente(empresa);
        pooled.setEstado("Abierta");
        pooled.setIban("ES70 2548 2154 2655");
        pooled.setFechaApertura(new Date());
        pooled.setCliente(empresa);
        listaPooled.add(pooled);

        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setId(new Long(1));
        pAutorizada.setIdentificacion("Y4001267V");
        pAutorizada.setNombre("Salva");
        pAutorizada.setApellidos("Ortiz");
        pAutorizada.setDireccion("Romanes");

        Autorizacion autorizacion = new Autorizacion();
        autorizacion.setAutorizadaId(pAutorizada);
        autorizacion.setEmpresaId(empresa);
        autorizacion.setTipo("Lectura");

        PAutorizada pAutorizada2 = new PAutorizada();
        pAutorizada2.setId(new Long(2));
        pAutorizada2.setIdentificacion("P90234823");
        pAutorizada2.setNombre("Mario");
        pAutorizada2.setApellidos("Martínez");
        pAutorizada2.setDireccion("La Cala");

        Autorizacion autorizacion2 = new Autorizacion();
        autorizacion2.setAutorizadaId(pAutorizada2);
        autorizacion2.setEmpresaId(empresa);
        autorizacion2.setTipo("Lectura");

        autorizaciones.add(autorizacion);
        autorizaciones.add(autorizacion2);

        empresa.setAutorizaciones(autorizaciones);

        setEsPooled(true);

        autorizaciones = empresa.getAutorizaciones();
        */

        //mostrarDatosCuenta("ES8400817251647192321264");
        String iban = "ES8400817251647192321264";

        //Pooled cuentaPooledAMostrar = gestionGetCuentas.getPooled(iban);
        Segregada cuentaSegregadaAMostrar = gestionGetCuentas.getSegregada(iban);

        if (cuentaSegregadaAMostrar != null){
            //mostrarSegregada(cuentaSegregadaAMostrar, iban);

            setEsSegregada(true);
            listaSegregada.add(cuentaSegregadaAMostrar);

            autorizaciones = gestionGetCuentas.getAutorizaciones(iban, cuentaSegregadaAMostrar.getCliente().getIdentificacion());
        }

    }

    public String eliminarAutorizacion(PAutorizada paut){
        //gestionEliminarAutorizados.darBaja();
        return null;
    }

    public void mostrarDatosCuenta(String iban) {
        Pooled cuentaPooledAMostrar = gestionGetCuentas.getPooled(iban);
        Segregada cuentaSegregadaAMostrar = gestionGetCuentas.getSegregada(iban);

        if (cuentaPooledAMostrar != null){
            //mostrarPooled(cuentaPooledAMostrar, iban);

            setEsPooled(true);
            listaPooled.add(cuentaPooledAMostrar);

            autorizaciones = gestionGetCuentas.getAutorizaciones(iban, cuentaPooledAMostrar.getCliente().getIdentificacion());

        } else if (cuentaSegregadaAMostrar != null){
            //mostrarSegregada(cuentaSegregadaAMostrar, iban);

            setEsSegregada(true);
            listaSegregada.add(cuentaSegregadaAMostrar);

            autorizaciones = gestionGetCuentas.getAutorizaciones(iban, cuentaSegregadaAMostrar.getCliente().getIdentificacion());

        }
    }

    public void mostrarPooled(Pooled cuentaPooledAMostrar, String iban){
        setEsPooled(true);
        listaPooled.add(cuentaPooledAMostrar);

        autorizaciones = gestionGetCuentas.getAutorizaciones(iban, cuentaPooledAMostrar.getCliente().getIdentificacion());

    }

    public void mostrarSegregada(Segregada cuentaSegregadaAMostrar, String iban){
        setEsSegregada(true);
        listaSegregada.add(cuentaSegregadaAMostrar);

        autorizaciones = gestionGetCuentas.getAutorizaciones(iban, cuentaSegregadaAMostrar.getCliente().getIdentificacion());

    }


}
