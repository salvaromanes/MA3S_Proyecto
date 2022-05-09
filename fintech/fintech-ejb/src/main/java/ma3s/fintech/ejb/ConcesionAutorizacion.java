package ma3s.fintech.ejb;

import ma3s.fintech.Autorizacion;
import ma3s.fintech.AutorizadaId;
import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.EmpresaNoExistenteException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ConcesionAutorizacion implements GestionConcesionAutorizacion{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void autorizarLectura(PAutorizada persona, Empresa empresa) throws PersonaNoExisteException, EmpresaNoExistenteException {
        AutorizadaId autorizadaId = new AutorizadaId();
        autorizadaId.setIdAutorizado(persona.getId());
        autorizadaId.setEmpresaId(empresa.getId());

        Query query = em.createQuery("select a from Autorizacion a");
        List<Autorizacion> autorizacion = query.getResultList();

        PAutorizada pAutorizada = em.find(PAutorizada.class, persona.getId());

        if(pAutorizada == null){
            throw new PersonaNoExisteException("La persona "+persona+" no existe");
        }

        Empresa emp = em.find(Empresa.class, empresa.getId());

        if(emp == null){
            throw new EmpresaNoExistenteException("La empresa "+empresa+" no existe");
        }

        int max = autorizacion.size();
        int i = 0;
        boolean encontrado = false;
        while(i < max && !encontrado){
            if(autorizacion.get(i).getEmpresaId().equals(emp) &&
                    autorizacion.get(i).getAutorizadaId().equals(pAutorizada)){
                encontrado = true;
            }else{
                i++;
            }
        }

        if(!encontrado){
            Autorizacion a = new Autorizacion();
            a.setEmpresaId(empresa);
            a.setAutorizadaId(persona);
            a.setTipo("Solo lectura");
            em.persist(a);
        }else{
            Autorizacion a = autorizacion.get(i);
            a.setEmpresaId(empresa);
            a.setTipo("Solo lectura");
            em.merge(a);
        }
    }

    @Override
    public void autorizarOperacion(PAutorizada persona, Empresa empresa) throws EmpresaNoExistenteException, PersonaNoExisteException {
        AutorizadaId autorizadaId = new AutorizadaId();
        autorizadaId.setIdAutorizado(persona.getId());
        autorizadaId.setEmpresaId(empresa.getId());

        Query query = em.createQuery("select a from Autorizacion a");
        List<Autorizacion> autorizacion = query.getResultList();

        PAutorizada pAutorizada = em.find(PAutorizada.class, persona.getId());

        if(pAutorizada == null){
            throw new PersonaNoExisteException("La persona "+persona+" no existe");
        }

        Empresa emp = em.find(Empresa.class, empresa.getId());

        if(emp == null){
            throw new EmpresaNoExistenteException("La empresa "+empresa+" no existe");
        }

        int max = autorizacion.size();
        int i = 0;
        boolean encontrado = false;
        while(i < max && !encontrado){
            if(autorizacion.get(i).getEmpresaId().equals(emp) &&
                    autorizacion.get(i).getAutorizadaId().equals(pAutorizada)){
                encontrado = true;
            }else{
                i++;
            }
        }

        if(!encontrado){
            Autorizacion a = new Autorizacion();
            a.setEmpresaId(empresa);
            a.setAutorizadaId(persona);
            a.setTipo("Operacion y lectura");
            em.persist(a);
        }else{
            Autorizacion a = autorizacion.get(i);
            a.setEmpresaId(empresa);
            a.setTipo("Operacion y lectura");
            em.merge(a);
        }
    }
}
