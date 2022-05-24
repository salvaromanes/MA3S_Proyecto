package ma3s.fintech.ejb;

import ma3s.fintech.Cliente;
import ma3s.fintech.Empresa;
import ma3s.fintech.Individual;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.CampoVacioException;
import ma3s.fintech.ejb.excepciones.ClienteYaExistenteException;
import ma3s.fintech.ejb.excepciones.PautorizadaYaExistenteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
public class AltaPAutorizada implements GestionAltaPAutorizada{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void darAltaPA(PAutorizada pAutorizada) throws CampoVacioException, PautorizadaYaExistenteException {
        if(pAutorizada == null)
            throw new CampoVacioException();
        String identificacion = pAutorizada.getIdentificacion();
        String nombre = pAutorizada.getNombre();
        String apellidos = pAutorizada.getApellidos();
        String estado = pAutorizada.getEstado();
        Date fecha_nacimiento = pAutorizada.getFechaNacimiento();
        Date fecha_inicio = pAutorizada.getFechaInicio();
        Date fecha_fin = pAutorizada.getFechaFin();
        String direccion = pAutorizada.getDireccion();
        if( identificacion == null || nombre == null
                || estado == null || apellidos == null
                || direccion == null || fecha_nacimiento == null
                || fecha_inicio == null || fecha_fin == null
                || direccion == null)
            throw new CampoVacioException();

        Query query = em.createQuery("select p from PAutorizada p");
        List<PAutorizada> listaPA = query.getResultList();

        for(PAutorizada p : listaPA){
            if(p.getIdentificacion().equals(identificacion))
                throw new PautorizadaYaExistenteException();
        }

        em.persist(pAutorizada);
    }

}
