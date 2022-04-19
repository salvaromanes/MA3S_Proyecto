import ma3s.fintech.Cuenta;
import ma3s.fintech.Pooled;
import ma3s.fintech.Segregada;
import ma3s.fintech.Usuario;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

        Cuenta cuenta = new Cuenta();
        cuenta.setIban("123");
        cuenta.setSwift("123");

        Pooled pooled = new Pooled();
        pooled.setIban("123");
        pooled.setSwift("123");

        Segregada segregada = new Segregada();
        segregada.setIban("123");
        segregada.setSwift("123");

        for (Cuenta c: new Cuenta[]{cuenta, pooled, segregada}) {
            em.persist(c);
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}