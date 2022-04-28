package ma3s.fintech.test;

import ma3s.fintech.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;


public class BaseDatos {
    public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Usuario usuario = new Usuario();
        usuario.setEsAdmin(true);
        usuario.setUser("Salva");
        usuario.setContrasena("malaga");

        Usuario usuario1 = new Usuario();
        usuario1.setEsAdmin(false);
        usuario1.setUser("MA3S");
        usuario1.setContrasena("ma3s");

        Cuenta cuenta = new Cuenta();
        cuenta.setIban("123456789012345");
        cuenta.setSwift("123846");

        Pooled pooled = new Pooled();
        pooled.setEstado("Activo");
        pooled.setFechaApertura(new Date());
        pooled.setIban("12345678");
        pooled.setSwift("12384");

        Segregada segregada = new Segregada();
        segregada.setEstado("Activa");
        segregada.setFechaApertura(new Date());
        segregada.setIban("12315");
        segregada.setSwift("12323");



        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setId(parseLong("1"));
        pAutorizada.setIdentificacion("12345678S");
        pAutorizada.setNombre("Salva");
        pAutorizada.setApellidos("Ortiz");
        pAutorizada.setDireccion("Romanes");

        PAutorizada pAutorizada2 = new PAutorizada();
        pAutorizada2.setId(parseLong("2"));
        pAutorizada2.setIdentificacion("999654111A");
        pAutorizada2.setNombre("Almudena");
        pAutorizada2.setApellidos("Balmont");
        pAutorizada2.setDireccion("Burgos");






        Empresa empresa = new Empresa();
        empresa.setId(parseLong("1"));
        empresa.setIdentificacion("UMA");
        empresa.setTipoCliente("Jurídico");
        empresa.setEstado("Activo");
        empresa.setFechaAlta(new Date());
        empresa.setDireccion("Bulevaur");
        empresa.setCiudad("Malaga");
        empresa.setCodigopostal("29004");
        empresa.setPais("Espana");
        empresa.setRazonSocial("UMA");




        Empresa empresa2 = new Empresa();
        empresa2.setId(parseLong("2"));
        empresa2.setIdentificacion("UCAM");
        empresa2.setTipoCliente("Jurídico");
        empresa2.setEstado("Activo");
        empresa2.setFechaAlta(new Date());
        empresa2.setDireccion("Bulevaur");
        empresa2.setCiudad("Murcia");
        empresa2.setCodigopostal("29845");
        empresa2.setPais("Espana");
        empresa2.setRazonSocial("UCAM");


        Autorizacion autorizacion = new Autorizacion();
        autorizacion.setAutorizadaId(pAutorizada);
        autorizacion.setEmpresaId(empresa);
        autorizacion.setTipo("Lectura");

        Cliente cliente = new Cliente();
        cliente.setId(223L);
        cliente.setIdentificacion("987654321A");
        cliente.setTipoCliente("Individual");
        cliente.setEstado("Activo");
        cliente.setFechaAlta(new Date());
        cliente.setDireccion("Concha Espina");
        cliente.setCiudad("Madrid");
        cliente.setCodigopostal("28001");
        cliente.setPais("Espana");





        Usuario user = new Usuario();
        user.setUser("Almu");
        user.setContrasena("1234");
        user.setEsAdmin(true);

        //---------DATOS PARA PRUEBAS DE LOS TEST DE CUENTAS

        Referencia referencia = new Referencia();
        referencia.setIban("123221");
        referencia.setEstado("Activa");
        referencia.setFechaApertura(new Date());
        referencia.setNombreBanco("Unicaja");
        referencia.setPais("Espana");
        referencia.setSaldo(85000);
        referencia.setSucursal("Unicaja-Huelin");

        Referencia referencia2 = new Referencia();
        referencia2.setIban("190599");
        referencia2.setEstado("Activa");
        referencia2.setFechaApertura(new Date());
        referencia2.setNombreBanco("Unicaja");
        referencia2.setPais("Espana");
        referencia2.setSaldo(0);
        referencia2.setSucursal("Unicaja-Teatinos");

        //--------Datos para pruebas de los tests de clientes

        //--DarAltaEmpresa
        Cliente cliente2 = new Cliente();
        cliente2.setId(new Long(83));
        cliente2.setIdentificacion("ES83");
        cliente2.setTipoCliente("Jurídico");
        cliente2.setEstado("Abierta");
        cliente2.setFechaAlta(new Date());
        cliente2.setDireccion("Bulevaur");
        cliente2.setCiudad("Málaga");
        cliente2.setCodigopostal("4562");
        cliente2.setPais("Spain");

        //--DarAltaIndividual
        Individual individual = new Individual();
        individual.setId(new Long(80));
        individual.setIdentificacion("MAN45");
        individual.setTipoCliente("Personal");
        individual.setEstado("Abierto");
        individual.setFechaAlta(new Date());
        individual.setDireccion("Bulevaur");
        individual.setCiudad("Málaga");
        individual.setCodigopostal("4562");
        individual.setPais("Spain");
        individual.setNombre("MANOLO");
        individual.setApellido("García");

        //--Modificar Empresa
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setId(new Long(20));
        nuevaEmpresa.setIdentificacion("ES20");
        nuevaEmpresa.setTipoCliente("Jurídico");
        nuevaEmpresa.setEstado("Abierta");
        nuevaEmpresa.setFechaAlta(new Date());
        nuevaEmpresa.setDireccion("Bulevaur");
        nuevaEmpresa.setCiudad("Málaga");
        nuevaEmpresa.setCodigopostal("4562");
        nuevaEmpresa.setPais("Spain");
        nuevaEmpresa.setRazonSocial("ES4558");

        //--Modificar Cliente
        Individual individual2 = new Individual();
        individual2.setId(new Long(30));
        individual2.setIdentificacion("MAN45");
        individual2.setTipoCliente("Personal");
        individual2.setEstado("Abierto");
        individual2.setFechaAlta(new Date());
        individual2.setDireccion("Bulevaur");
        individual2.setCiudad("Málaga");
        individual2.setCodigopostal("4562");
        individual2.setPais("Spain");
        individual2.setNombre("MANOLO");
        individual2.setApellido("García");

        //--Dar Baja Cliente
        //Sin errores
        Cliente cliente3 = new Cliente();
        cliente3.setId(new Long(40));
        cliente3.setIdentificacion("ES40");
        cliente3.setTipoCliente("Jurídico");
        cliente3.setEstado("Abierta");
        cliente3.setFechaAlta(new Date());
        cliente3.setDireccion("Bulevaur");
        cliente3.setCiudad("Málaga");
        cliente3.setCodigopostal("4562");
        cliente3.setPais("Spain");

        //----Error Cuenta abierta
        Cliente cliente4 = new Cliente();
        cliente4.setId(new Long(450));
        cliente4.setIdentificacion("ES450");
        cliente4.setTipoCliente("Jurídico");
        cliente4.setEstado("Abierta");
        cliente4.setFechaAlta(new Date());
        cliente4.setDireccion("Bulevaur");
        cliente4.setCiudad("Málaga");
        cliente4.setCodigopostal("4562");
        cliente4.setPais("Spain");

        //Relacion con cuenta
        Fintech cuentaAbierta = new Fintech();
        cuentaAbierta.setEstado("Abierta");
        cuentaAbierta.setFechaApertura(new Date());
        cuentaAbierta.setIban("ES986542");
        cuentaAbierta.setSwift("ES986542");
        cuentaAbierta.setCliente(cliente4);

        //List<Fintech> listaCuentas = new ArrayList<>();
        //listaCuentas.add(cuentaAbierta);
        //cliente4.setCuentasFintech(listaCuentas);

        for (Usuario u: new Usuario[]{usuario, usuario1, user}) {
            em.persist(u);
        }

        for (Cuenta c: new Cuenta[]{cuenta, pooled, segregada, cuentaAbierta}) {
            em.persist(c);
        }

        for (PAutorizada pa: new PAutorizada[]{pAutorizada, pAutorizada2}) {
            em.persist(pa);
        }

        for (Cliente pa: new Cliente[]{cliente, cliente2, cliente3, cliente4}) {
            em.persist(pa);
        }

        for (Empresa emp : new Empresa[]{empresa, nuevaEmpresa, empresa2}){
            em.persist(emp);
        }

        for (Individual ind : new Individual[]{individual, individual2}){
            em.persist(ind);
        }

        for (Referencia ref : new Referencia[]{referencia, referencia2}){
            em.persist(ref);
        }

        for (Autorizacion au : new Autorizacion[]{autorizacion}){
            em.persist(au);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}