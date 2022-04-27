package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.NoEsPAutorizadaException;
import ma3s.fintech.ejb.excepciones.PersonaNoExisteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless
public class EliminarAutorizados implements GestionEliminarAutorizados{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void darBaja(String usuario, Long idPA, Long idEmpresa) throws PersonaNoExisteException, NoEsPAutorizadaException {
        PAutorizada pA = em.find(PAutorizada.class, idPA);
        Usuario admin = em.find(Usuario.class, usuario);
        Autorizacion autorizacion = em.find(Autorizacion.class, pA.getId());
        Empresa empresa = em.find(Empresa.class, idEmpresa);

        if (admin == null || !admin.getEsAdmin()){
            throw new PersonaNoExisteException("El administrativo " + admin + " no existe");
        }

        if(pA == null){
            throw new PersonaNoExisteException("La persona autorizada con id " + pA.getId() + " no existe");
        }

        if(autorizacion == null){
            throw new NoEsPAutorizadaException("La persona autorizada no tiene autorizaciones");
        }

        if(!autorizacion.getAutorizadaId().getId().equals(pA.getId()) && !autorizacion.getEmpresaId().getId().equals(empresa.getId())){
            throw new NoEsPAutorizadaException();
        }

        em.remove(autorizacion);
    }
}
