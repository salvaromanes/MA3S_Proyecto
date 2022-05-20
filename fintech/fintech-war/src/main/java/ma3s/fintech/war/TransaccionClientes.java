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
    private Cuenta cuenta;
    private Autorizacion autorizacion;
    private PAutorizada autorizada;
    private Usuario usuario;
    private Pooled pooled;

    private Cliente cliente;
    private Empresa empresa;

    private String div;
    private double cantidad;

    public Pooled getPooled() {
        return pooled;
    }

    public void setPooled(Pooled pooled) {
        this.pooled = pooled;
    }

    public Autorizacion getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public PAutorizada getAutorizada() {
        return autorizada;
    }

    public void setAutorizada(PAutorizada autorizada) {
        this.autorizada = autorizada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String realizarTransaccionCliente() {
        Transaccion t = new Transaccion();
        try {
            if(!cliente.getTipoCliente().equalsIgnoreCase("FISICA") ||
                    !autorizacion.getEmpresaId().equals(empresa.getId()) ||
                    cuenta.getIban() == pooled.getIban()){
            }

            gestionTransferencia.transferenciaCliente(t, cliente.getId());
            pooled.setSaldo(getPooled().getSaldo() + cantidad);

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

