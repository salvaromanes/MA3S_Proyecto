package ma3s.fintech;

import ma3s.fintech.excepciones.PersonaNoExisteException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

public class GenerarCSV implements GestionGenerarCSV{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;


    @Override
    public void generarCSV(String usuario, String tipoInforme, Date ultimoReporte) throws PersonaNoExisteException {
        Usuario user = em.find(Usuario.class, usuario);
        if(!isAdministrativo(user.getUser())){
            throw new PersonaNoExisteException("El usuario " + usuario + " no existe");
        }

        if (tipoInforme.equals("Inicial")){
            reporteInicial();
        }else{ // el informe es periodico
            ReportesPeriodicos();
        }
    }

    private void reporteInicial() {
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
