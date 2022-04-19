package ma3s.fintech;

import ma3s.fintech.excepciones.UsuarioNoEncontradoException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ConcesionAutorizacion implements GestionConcesionAutorizacion{
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;

    public void autorizarLectura(String nombre, String apellidos, String identificacion) throws UsuarioNoEncontradoException{
        PAutorizada autorizado = em.find(PAutorizada.class, identificacion);
        

        if(autorizado == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizado.getNombre() == nombre && autorizado.getApellidos() == apellidos){
            autorizado.setTipo("Solo lectura");
        }
    }

    public void autorizarOperacion(String nombre, String apellidos, String identificacion) throws UsuarioNoEncontradoException{
        PAutorizada autorizado = em.find(PAutorizada.class, identificacion);

        if(autorizado == null){
            throw new UsuarioNoEncontradoException();
        }

        if(autorizado.getNombre() == nombre && autorizado.getApellidos() == apellidos){
            autorizado.setTipo("Operacion y lectura");
        }
    }
}
