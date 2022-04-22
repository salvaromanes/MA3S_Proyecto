package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class GenerarCSV implements GestionGenerarCSV{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;


    @Override
    public void generarCSV(String usuario, String tipoInforme, Date ultimoReporte) throws PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);
        Query query = em.createQuery("select c from Cliente c");
        List<Cliente> listaClientes = query.getResultList();

        if(!isAdministrativo(user.getUser())){
            throw new PersonaNoExisteException("El usuario " + usuario + " no existe");
        }

        if (tipoInforme.equals("Inicial")){
            reporteInicial(listaClientes);
        }else{ // el informe es periodico
            ReportesPeriodicos();
        }
    }

    private void reporteInicial(List<Cliente> lista) {
        for (Cliente c : lista) {
            List<Fintech> cuentasCliente = c.getCuentasFintech();
            for (Fintech f : cuentasCliente) {
                Date fechaActual = Date.from(Instant.from(LocalDateTime.now()));
                if(f.getFechaApertura().compareTo(fechaActual) == 0){
                    /// meter datos de la lista
                }
            }
        }
    }

    private void ReportesPeriodicos() {
    }


    // ¿el usuario es administrativo? sirve para poder gestionar las bajas
    @Override
    public boolean isAdministrativo(String usuario) throws PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);
        if(!user.getUser().equals(usuario)){
            throw new PersonaNoExisteException("El usuario " + usuario + " no existe");
        }
        return user.getEsAdmin();
    }
}
