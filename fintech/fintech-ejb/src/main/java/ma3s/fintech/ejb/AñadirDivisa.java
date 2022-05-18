package ma3s.fintech.ejb;

import ma3s.fintech.Divisa;
import ma3s.fintech.Usuario;
import ma3s.fintech.ejb.excepciones.DivisaExistenteException;
import ma3s.fintech.ejb.excepciones.UsuarioIncorrectoException;
import ma3s.fintech.ejb.excepciones.UsuarioNoEncontradoException;
import ma3s.fintech.ejb.excepciones.ValorNoValidoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AñadirDivisa implements GestionAñadirDivisa {
    @PersistenceContext(unitName = "FintechEjb")
    private EntityManager em;

    private void comprobarAdministrador(String usuario) throws UsuarioIncorrectoException, UsuarioNoEncontradoException {
        Usuario user = em.find(Usuario.class, usuario);

        if(user == null){
            throw new UsuarioNoEncontradoException("El usuario "+usuario+" no ha sido encontrado");
        }else if(!user.getEsAdmin()){
            throw new UsuarioIncorrectoException("El usuario "+usuario+" no es administrador");
        }
    }

    public void anadirNuevaDivisa(Divisa divisa, String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException {
        Divisa d = em.find(Divisa.class, divisa.getAbreviatura());

        comprobarAdministrador(usuario);

        if(d != null){
            throw new DivisaExistenteException("La divisa "+divisa.getNombre()+" ya existe");
        }

        Divisa div = new Divisa();
        div.setAbreviatura(divisa.getAbreviatura());
        div.setNombre(divisa.getNombre());
        div.setSimbolo(divisa.getSimbolo());
        div.setCambioEuro(divisa.getCambioEuro());

        em.persist(div);
    }

    public void editarDivisa(Double cambio, Divisa divisa, String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException, ValorNoValidoException {
        Divisa d = em.find(Divisa.class, divisa.getAbreviatura());

        comprobarAdministrador(usuario);

        if(d == null){
            throw new DivisaExistenteException("La divisa "+divisa.getNombre()+" no existe");
        }

        if(cambio > 0){
            Divisa div = new Divisa();
            div.setCambioEuro(cambio);
            em.merge(div);
        }else {
            throw new ValorNoValidoException("El valor de cambio no es valido");
        }
    }

    public void eliminarDivisa(Divisa divisa, String usuario) throws UsuarioNoEncontradoException, UsuarioIncorrectoException, DivisaExistenteException {
        Divisa d = em.find(Divisa.class, divisa.getAbreviatura());

        comprobarAdministrador(usuario);

        if(d == null){
            throw new DivisaExistenteException("La divisa "+divisa.getNombre()+" no existe");
        }

        em.remove(d);
    }
}
