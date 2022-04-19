import ma3s.fintech.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static java.lang.Long.parseLong;


public class BaseDatos {
    public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Usuario usuario = new Usuario();
        usuario.setEsAdmin(true);
        usuario.setUser("Salva");

        Usuario usuario1 = new Usuario();
        usuario1.setEsAdmin(false);
        usuario1.setUser("MA3S");

        Cuenta cuenta = new Cuenta();
        cuenta.setIban("123");
        cuenta.setSwift("123");

        Pooled pooled = new Pooled();
        pooled.setIban("123");
        pooled.setSwift("123");

        Segregada segregada = new Segregada();
        segregada.setIban("123");
        segregada.setSwift("123");

        PAutorizada pAutorizada = new PAutorizada();
        pAutorizada.setNombre("Salva");
        pAutorizada.setApellidos("Ortiz");
        pAutorizada.setIdentificacion("12345678S");
        pAutorizada.setId(parseLong("1", 1));

        for (Usuario u: new Usuario[]{usuario, usuario1}) {
            em.persist(u);
        }

        for (Cuenta c: new Cuenta[]{cuenta, pooled, segregada}) {
            em.persist(c);
        }

        for (PAutorizada pa: new PAutorizada[]{pAutorizada}) {
            em.persist(pa);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}