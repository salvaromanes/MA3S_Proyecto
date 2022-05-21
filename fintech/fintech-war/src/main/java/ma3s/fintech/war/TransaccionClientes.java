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
    private GestionTransferencia gestionTransferencia;

    private Transaccion transaccion;

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public String realizarTransaccionCliente() {
        Cliente cliente = new Cliente();
        try {
            gestionTransferencia.transferenciaCliente(transaccion, cliente.getId());
            return "https://www.diariosur.es/";
        } catch (PersonaNoExisteException e) {
            throw new RuntimeException(e);
        } catch (CampoVacioException e) {
            throw new RuntimeException(e);
        } catch (ErrorOrigenTransaccionException e) {
            throw new RuntimeException(e);
        } catch (SaldoNoSuficiente e) {
            throw new RuntimeException(e);
        }

    }
}