package ma3s.fintech.test;

import ma3s.fintech.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Date;

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
        pooled.setIban("12345678");
        pooled.setSwift("12384");
        pooled.setEstado("Activo");
        pooled.setFechaApertura(new Date());



        Segregada segregada = new Segregada();
        segregada.setIban("12315");
        segregada.setSwift("12323");
        segregada.setEstado("Activa");
        segregada.setFechaApertura(new Date());


        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setNombre("Salva");
        pAutorizada.setApellidos("Ortiz");
        pAutorizada.setIdentificacion("12345678S");
        pAutorizada.setId(parseLong("1"));
        pAutorizada.setDireccion("Romanes");

        Empresa empresa = new Empresa();
        empresa.setIdentificacion("UMA");
        empresa.setRazonSocial("UMA");
        empresa.setCiudad("Malaga");
        empresa.setCodigopostal("29004");
        empresa.setIdentificacion("Avenia Plutarco");
        empresa.setEstado("Activo");
        empresa.setFechaAlta(new Date());
        empresa.setPais("Espana");
        empresa.setId(parseLong("1"));


        Cliente cliente = new Cliente();
        cliente.setIdentificacion("987654321A");
        cliente.setId(parseLong("223"));
        cliente.setCiudad("Madrid");
        cliente.setCodigopostal("28001");
        cliente.setDireccion("Concha Espina");
        cliente.setEstado("Activo");
        cliente.setFechaAlta(new Date());
        cliente.setPais("Espana");
        cliente.setTipoCliente("Individual");




        Usuario user = new Usuario();
        user.setUser("Almu");
        user.setContrasena("1234");
        user.setEsAdmin(true);


        Referencia referencia = new Referencia();
        referencia.setIban("123221");
        referencia.setEstado("Activa");
        referencia.setFechaApertura(new Date());
        referencia.setNombreBanco("Unicaja");
        referencia.setPais("Espana");
        referencia.setSaldo(85000);
        referencia.setSucursal("Unicaja-Huelin");


        for (Usuario u: new Usuario[]{usuario, usuario1, user}) {
            em.persist(u);
        }

        for (Cuenta c: new Cuenta[]{cuenta, pooled, segregada}) {
            em.persist(c);
        }

        for (PAutorizada pa: new PAutorizada[]{pAutorizada}) {
            em.persist(pa);
        }

        for (Cliente pa: new Cliente[]{cliente}) {
            em.persist(pa);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}