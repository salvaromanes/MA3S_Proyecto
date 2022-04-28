package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class AnadirAutorizados implements GestionAnadirAutorizados {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;


    private void comprobarAdministrador(String usuario) throws NoEsAdministrativoException, PersonaNoExisteException {
        Usuario us1 = em.find(Usuario.class,usuario);

        if(us1 == null){
            throw new PersonaNoExisteException();
        }

        if(!us1.getUser().equals(usuario)){
            throw new PersonaNoExisteException("La persona : " + us1.getUser() + " no se encuentra");
        }

        if(us1.getEsAdmin() == false){
            throw new NoEsAdministrativoException("El usuario pasado: " + us1.getUser() + " no es adminitrativo");
        }

    }


    @Override
    public void anadirPAut(PAutorizada autorizada, Empresa empresa, String usuario) throws NoEsPAutorizadaException, EmpresaNoExistenteException, PersonaNoExisteException, EmpresaNoRelacException, NoEsAdministrativoException {
      if(autorizada == null || empresa == null || usuario == null){
            throw new PersonaNoExisteException();
        }


        PAutorizada p = em.find(PAutorizada.class,autorizada.getId());


        if(p == null){
            throw  new NoEsPAutorizadaException("La persona con usuario : " + p.getUser() + " no es persona autorizada");
        }


        Empresa e = em.find(Empresa.class,empresa.getId());

        if(e == null){
            throw  new EmpresaNoExistenteException("La empresa : " + e.getId() + " no se encuentra");
        }

        AutorizadaId aux = new AutorizadaId();
        aux.setEmpresaId(e.getId());
        aux.setIdAutorizado(p.getId());

        Autorizacion a = em.find(Autorizacion.class , aux);

        if(a != null){
            throw new NoEsPAutorizadaException( "La persona autorizada ya tiene autorizacion");
        }

        comprobarAdministrador(usuario);

        Autorizacion autorizar = new Autorizacion();
        autorizar.setAutorizadaId(p);
        autorizar.setEmpresaId(e);
        autorizar.setTipo("Operacion y lectura");
        em.persist(autorizar);

    }



}
