package ma3s.fintech;

import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ConcesionAutorizacion implements GestionConcesionAutorizacion{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    @Override
    public void autorizarLectura(Long idPersona, Empresa empresa) throws UsuarioNoEncontradoException{
        PAutorizada autorizado = em.find(PAutorizada.class, idPersona);
        List<Autorizacion> autorizacion = (List<Autorizacion>) em.find(Autorizacion.class, idPersona);

        if(autorizado == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizacion == null){
            throw new UsuarioNoEncontradoException();
        }

        int max = autorizacion.size();
        int i = 0;
        boolean encontrado = false;
        while(i < max && !encontrado){
            if(autorizacion.get(i).getEmpresaId().equals(empresa)){
                encontrado = true;
            }else{
                i++;
            }
        }

        if(!encontrado){
            Autorizacion a = new Autorizacion();
            a.setEmpresaId(empresa);
            a.setAutorizadaId(autorizado);
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
    public void autorizarOperacion(Long idPersona, Empresa empresa) throws UsuarioNoEncontradoException{
        PAutorizada autorizado = em.find(PAutorizada.class, idPersona);
        List<Autorizacion> autorizacion = (List<Autorizacion>) em.find(Autorizacion.class, idPersona);

        if(autorizado == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizacion == null){
            throw new UsuarioNoEncontradoException();
        }

        int max = autorizacion.size();
        int i = 0;
        boolean encontrado = false;
        while(i < max && !encontrado){
            if(autorizacion.get(i).getEmpresaId().equals(empresa)){
                encontrado = true;
            }else{
                i++;
            }
        }

        if(!encontrado){
            Autorizacion a = new Autorizacion();
            a.setEmpresaId(empresa);
            a.setAutorizadaId(autorizado);
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
