package ma3s.fintech.war;


import ma3s.fintech.*;
import ma3s.fintech.PAutorizada;
import ma3s.fintech.ejb.GestionTransferencia;
import ma3s.fintech.ejb.excepciones.*;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "transcliente")
@RequestScoped
public class TransaccionClientes {

    @Inject
    private Sesion sesion;
    @Inject
    private GestionTransferencia gestionTransferencia;

    Pooled pooled;
    Segregada segregada;
    private Transaccion transaccion;

    public Pooled getPooled() {
        return pooled;
    }

    public Segregada getSegregada() {
        return segregada;
    }

    public void setPooled(Pooled pooled) {
        this.pooled = pooled;
    }

    public void setSegregada(Segregada segregada) {
        this.segregada = segregada;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public boolean esTipo(){
        boolean res = true;
        if(!sesion.getPooled().equals(pooled.getIban())){
            res = false;
        }
        return res;
    }

    public String realizarTransaccionCliente() {
        try {
            if(esTipo() == true){
                transaccion.setCuentaOrigen(pooled);
                gestionTransferencia.transferenciaCliente(transaccion,sesion.getUsuario().getCliente().getId());
            }else{
                transaccion.setCuentaOrigen(segregada);
                gestionTransferencia.transferenciaCliente(transaccion,sesion.getUsuario().getCliente().getId());
            }

        } catch (PersonaNoExisteException e) {
            throw new RuntimeException(e);
        } catch (CampoVacioException e) {
            throw new RuntimeException(e);
        } catch (ErrorOrigenTransaccionException e) {
            throw new RuntimeException(e);
        } catch (SaldoNoSuficiente e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}