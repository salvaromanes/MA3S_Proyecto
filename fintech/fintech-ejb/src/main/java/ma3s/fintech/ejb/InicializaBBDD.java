package ma3s.fintech.ejb;

import ma3s.fintech.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ma3s.fintech.DepositadaEn;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

@Singleton
@Startup
public class InicializaBBDD {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @PostConstruct
    public void inicializar(){
        Empresa comprobacion = em.find(Empresa.class,new Long(1));

        if(comprobacion != null){
            return;
        }
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

        Individual individual = new Individual();
        individual.setId(new Long(2));
        individual.setIdentificacion("63937528N");
        individual.setTipoCliente("Individual");
        individual.setEstado("Abierto");
        individual.setFechaAlta(new Date());
        individual.setDireccion("Bulevaur");
        individual.setCiudad("Málaga");
        individual.setCodigopostal("29004");
        individual.setPais("Spain");
        individual.setNombre("Manolo");
        individual.setApellido("García");

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

        Divisa dolar = new Divisa();
        dolar.setAbreviatura("USD");
        dolar.setNombre("Dollar");
        dolar.setSimbolo((char) 36);
        dolar.setCambioEuro((double) 1);

        Referencia referencia1 = new Referencia();
        referencia1.setIban("VG57DDVS5173214965983931");
        referencia1.setEstado("Activa");
        referencia1.setFechaApertura(new Date());
        referencia1.setNombreBanco("Unicaja");
        referencia1.setPais("Espana");
        referencia1.setSaldo(85000);
        referencia1.setSucursal("Unicaja-Huelin");
        referencia1.setDivisa(dolar);

        Segregada segregada1 = new Segregada();
        segregada1.setEstado("Activa");
        segregada1.setFechaApertura(new Date());
        segregada1.setIban("NL63ABNA6548268733");
        segregada1.setSwift("12323");
        segregada1.setCliente(empresa);
        segregada1.setReferencia(referencia1);

        Divisa euro = new Divisa();
        euro.setAbreviatura("EUR");
        euro.setNombre("Euro");
        euro.setSimbolo((char) 8364);
        euro.setCambioEuro((double) 1);

        Referencia referencia2 = new Referencia();
        referencia2.setIban("HN47QUXH11325678769785549996");
        referencia2.setEstado("Activa");
        referencia2.setFechaApertura(new Date());
        referencia2.setNombreBanco("Santander");
        referencia2.setPais("Espana");
        referencia2.setSaldo(80000);
        referencia2.setSucursal("Santander-Malaga");
        referencia2.setDivisa(dolar);

        Segregada segregada2 = new Segregada();
        segregada2.setEstado("Activa");
        segregada2.setFechaApertura(new Date());
        segregada2.setIban("FR5514508000502273293129K55");
        segregada2.setSwift("12323");
        segregada2.setCliente(empresa);
        segregada2.setReferencia(referencia2);

        Referencia referencia3 = new Referencia();
        referencia3.setIban("HN47QUXH113256787697855");
        referencia3.setEstado("Cerrada");
        referencia3.setFechaApertura(new Date());
        referencia3.setNombreBanco("BBVA");
        referencia3.setPais("Espana");
        referencia3.setSaldo(0);
        referencia3.setSucursal("BBVA-Madrid");
        referencia3.setDivisa(dolar);

        Segregada segregada3 = new Segregada();
        segregada3.setEstado("Cerrada");
        segregada3.setFechaApertura(new Date());
        Date fechaCierre = new Date(2021, Calendar.SEPTEMBER,1);
        segregada3.setFechaCierre(fechaCierre);
        segregada3.setIban("DE31500105179261215675");
        segregada3.setSwift("12323");
        segregada3.setCliente(empresa);
        segregada3.setReferencia(referencia2);

        Divisa libra = new Divisa();
        libra.setAbreviatura("LB");
        libra.setNombre("Libra");
        libra.setSimbolo((char) 156);
        libra.setCambioEuro((double) 1);

        Referencia referencia4 = new Referencia();
        referencia4.setIban("ES7121007487367264321882");
        referencia4.setEstado("Activa");
        referencia4.setFechaApertura(new Date());
        referencia4.setNombreBanco("BBVA");
        referencia4.setPais("Espana");
        referencia4.setSaldo(2000);
        referencia4.setSucursal("BBVA-Barcelona");
        referencia4.setDivisa(euro);

        Referencia referencia5 = new Referencia();
        referencia5.setIban("VG88HBIJ4257959912673134");
        referencia5.setEstado("Activa");
        referencia5.setFechaApertura(new Date());
        referencia5.setNombreBanco("Santander");
        referencia5.setPais("Espana");
        referencia5.setSaldo(1000);
        referencia5.setSucursal("Santander-Madrid");
        referencia5.setDivisa(dolar);

        Referencia referencia6 = new Referencia();
        referencia6.setIban("GB79BARC20040134265953");
        referencia6.setEstado("Activa");
        referencia6.setFechaApertura(new Date());
        referencia6.setNombreBanco("Unicaja");
        referencia6.setPais("Espana");
        referencia6.setSaldo(3000);
        referencia6.setSucursal("Unicaja-Galicia");
        referencia6.setDivisa(dolar);



        Pooled pooled = new Pooled();
        pooled.setEstado("Activo");
        pooled.setFechaApertura(new Date());
        pooled.setCliente(individual);
        pooled.setIban("ES8400817251647192321264");
        pooled.setSwift("12384");


        DepositadaEn depositada1 = new DepositadaEn();
        depositada1.setReferencia(referencia4);
        depositada1.setIbanPooled(pooled);
        depositada1.setSaldo((double) 100);

        DepositadaEn depositada2 = new DepositadaEn();
        depositada2.setReferencia(referencia5);
        depositada2.setIbanPooled(pooled);
        depositada2.setSaldo((double) 200);

        DepositadaEn depositada3 = new DepositadaEn();
        depositada3.setReferencia(referencia6);
        depositada3.setIbanPooled(pooled);
        depositada3.setSaldo((double) 134);

        List<DepositadaEn> listaDepositadas = new ArrayList<>();
        listaDepositadas.add(depositada1);
        listaDepositadas.add(depositada2);
        listaDepositadas.add(depositada3);
        pooled.setDepositos(listaDepositadas);

        Transaccion transaccion = new Transaccion();
        transaccion.setIdUnico("ASDF546");
        transaccion.setFechaInstruccion(new Date());
        transaccion.setCantidad((double)200);
        transaccion.setTipo("Ingreso");
        transaccion.setCuentaOrigen(segregada1);
        transaccion.setCuentaDestino(pooled);
        transaccion.setDivisaEmisor(dolar);
        transaccion.setDivisaReceptor(dolar);

        Usuario usuario1 = new Usuario();
        usuario1.setUser("juan");
        usuario1.setContrasena("juan");
        usuario1.setEsAdmin(false);
        usuario1.setCliente(individual);

        Usuario usuario2 = new Usuario();
        usuario2.setUser("ana");
        usuario2.setContrasena("ana");
        usuario2.setEsAdmin(false);
        usuario2.setAutorizada(pAutorizada);

        Usuario usuario3 = new Usuario();
        usuario3.setUser("ponciano");
        usuario3.setContrasena("ponciano");
        usuario3.setEsAdmin(true);


        for (Empresa e : new Empresa[]{empresa}){
            em.persist(e);
        }

        for (Individual i : new Individual[] {individual}){
            em.persist(i);
        }

        for (PAutorizada p : new PAutorizada[]{pAutorizada}){
            em.persist(p);
        }

        for (Autorizacion a : new Autorizacion[]{autorizacion}){
            em.persist(a);
        }

        for (Divisa d : new Divisa[]{dolar, euro, libra}){
            em.persist(d);
        }

        for (Referencia r : new Referencia[]{referencia1, referencia2, referencia3, referencia4, referencia5, referencia6}){
            em.persist(r);
        }

        for (Segregada s : new Segregada[]{segregada1, segregada2, segregada3}){
            em.persist(s);
        }

        for (Pooled p : new Pooled[]{pooled}){
            em.persist(p);
        }

        for (Transaccion t: new Transaccion[]{transaccion}){
            em.persist(t);
        }

        for (DepositadaEn d : new DepositadaEn[]{depositada1, depositada2, depositada3}){
            em.persist(d);
        }

        for (Usuario u: new Usuario[]{usuario1, usuario2, usuario3}) {
            em.persist(u);
        }

    }
}
