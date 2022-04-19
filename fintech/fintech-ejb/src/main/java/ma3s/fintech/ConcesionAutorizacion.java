package ma3s.fintech;

import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConcesionAutorizacion implements GestionConcesionAutorizacion{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    public void autorizarLectura(String nombre, String apellidos, String identificacion, Long id) throws UsuarioNoEncontradoException{
        PAutorizada autorizado = em.find(PAutorizada.class, id);
        Autorizacion autorizacion =  em.find(Autorizacion.class, id);

        if(autorizado == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizacion == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizado.getNombre() == nombre && autorizado.getApellidos() == apellidos && autorizado.getIdentificacion() == identificacion){
            autorizacion.setTipo("Solo lectura");
        }
    }

    public void autorizarOperacion(String nombre, String apellidos, String identificacion, Long id) throws UsuarioNoEncontradoException{
        PAutorizada autorizado = em.find(PAutorizada.class, identificacion);
        Autorizacion autorizacion =  em.find(Autorizacion.class, id);

        if(autorizado == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizacion == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizado.getNombre() == nombre && autorizado.getApellidos() == apellidos && autorizado.getIdentificacion() == identificacion){
            autorizado.setTipo("Operacion y lectura");
        }
    }
}
