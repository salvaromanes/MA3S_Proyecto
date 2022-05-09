package ma3s.fintech.ejb;

import ma3s.fintech.*;
import ma3s.fintech.ejb.excepciones.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EliminarAutorizados implements GestionEliminarAutorizados{
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    @Override
    public void darBaja(String usuario, Long idPA, Long idEmpresa) throws PersonaNoExisteException, NoEsPAutorizadaException, EmpresaNoExistenteException, DatosIncorrectosException {

        if(idPA == null){
            throw new DatosIncorrectosException("darBaja: el dato introducido 'id' de persona autorizada no es valido");
        }

        if(idEmpresa == null){
            throw new DatosIncorrectosException("darBaja: el dato introducido 'id' de empresa no es valido");
        }

        Empresa empresa = em.find(Empresa.class, idEmpresa);
        if(empresa == null){
            throw new EmpresaNoExistenteException("darBaja: empresa error");
        }

        PAutorizada pA = em.find(PAutorizada.class, idPA);
        if(pA == null){
            throw new PersonaNoExisteException("darBaja: La persona autorizada no existe");
        }

        Usuario admin = em.find(Usuario.class, usuario);
        if (admin == null || !admin.getEsAdmin()){
            throw new PersonaNoExisteException("darBaja: El administrativo no existe");
        }

        AutorizadaId autid = new AutorizadaId();
        autid.setIdAutorizado(pA.getId());
        autid.setEmpresaId(empresa.getId());

        Autorizacion autorizacion = em.find(Autorizacion.class, autid);
        if(autorizacion == null){
            throw new NoEsPAutorizadaException("darBaja: La persona autorizada no tiene autorizaciones");
        }

        if(!autorizacion.getAutorizadaId().getId().equals(pA.getId()) && !autorizacion.getEmpresaId().getId().equals(empresa.getId())){
            throw new NoEsPAutorizadaException("darBaja: no hay autorizacion error");
        }

        em.remove(autorizacion);
    }
}
