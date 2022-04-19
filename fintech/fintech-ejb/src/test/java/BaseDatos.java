import ma3s.fintech.Cuenta;
import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


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

        for (Usuario u: new Usuario[]{usuario, usuario1}) {
            em.persist(u);
        }

        for (Cuenta c: new Cuenta[]{cuenta, pooled, segregada}) {
            em.persist(c);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}