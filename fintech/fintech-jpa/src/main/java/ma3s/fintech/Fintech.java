package ma3s.fintech;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Fintech extends Cuenta implements Serializable {
    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaApertura;

    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    private String clasificacion;

    @ManyToOne()
    private Cliente cliente;

    public Fintech(){
        super();
    }

    public String getEstado(){
        return estado;
    }

    public Date getFechaApertura(){
        return fechaApertura;
    }

    public Date getFechaCierre(){
        return fechaCierre;
    }

    public String getClasificacion(){
        return clasificacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setEstado(String e){
        estado=e;
    }

    public void setFechaApertura(Date f){
        fechaApertura =f;
    }

    public void setFechaCierre(Date f){
        fechaCierre =f;
    }

    public void setClasificacion(String c){
        clasificacion=c;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return  super.toString() + "Cuenta Fintech{\n" +
                "estado=" + estado +
                "\nfecha_apertura=" + fechaApertura +
                "\nfecha_cierre=" + fechaCierre +
                "\nclasificacion=" + clasificacion +
                "\ncliente=" + cliente +
                '}';
    }
}
