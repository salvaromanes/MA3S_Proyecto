package ma3s.fintech.ejb;

import ma3s.fintech.Autorizacion;
import ma3s.fintech.AutorizadaId;
import ma3s.fintech.Empresa;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ConcesionAutorizacion implements GestionConcesionAutorizacion{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void autorizarLectura(PAutorizada persona, Empresa empresa) throws UsuarioNoEncontradoException{
        AutorizadaId autorizadaId = new AutorizadaId();
        autorizadaId.setIdAutorizado(persona.getId());
        autorizadaId.setEmpresaId(empresa.getId());
        List<Autorizacion> autorizacion = (List<Autorizacion>) em.find(Autorizacion.class, autorizadaId);

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
    public void autorizarOperacion(PAutorizada persona, Empresa empresa) throws UsuarioNoEncontradoException{
        AutorizadaId autorizadaId = new AutorizadaId();
        autorizadaId.setIdAutorizado(persona.getId());
        autorizadaId.setEmpresaId(empresa.getId());
        List<Autorizacion> autorizacion = (List<Autorizacion>) em.find(Autorizacion.class, autorizadaId);

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
