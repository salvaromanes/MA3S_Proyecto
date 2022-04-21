package ma3s.fintech;

import ma3s.fintech.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless

public class AnadirAutorizados implements GestionAnadirAutorizados {
    @PersistenceContext(name="FintechEjb")
    private EntityManager em;


    @Override
    public void comprobarAdministrador(String usuario) throws NoEsAdministrativoException, PersonaNoExisteException {
        Usuario us1 = em.find(Usuario.class,usuario);

        if(!us1.equals(usuario)){
            throw new PersonaNoExisteException("La persona : " + us1.getUser() + " no se encuentra");
        }

        if(us1.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario pasado: " + us1.getUser() + " no es adminitrativo");
        }

    }


    // hay que ver como añadir la persona a la cuenta de la empresa

    @Override
    public void anadirPAut(PAutorizada autorizada, Empresa empresa) throws NoEsPAutorizadaException, EmpresaNoExistenteException {
        PAutorizada p = em.find(PAutorizada.class,autorizada);
        Empresa e = em.find(Empresa.class,empresa);

        if(!p.getUser().equals(autorizada)){
            throw  new NoEsPAutorizadaException("La persona con usuario : " + p.getUser() + " no es persona autorizada");
        }

        if(!e.getId().equals(empresa)) {
            throw  new EmpresaNoExistenteException("La empresa : " + e.getId() + " no se encuentra");
        }
    }


}
